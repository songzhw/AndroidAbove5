package cn.six.a6x.fingerprint.mariotaku;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import cn.six.a6x.R;

// see : https://github.com/mariotaku/FingerprintSample

public class MariotakuFingerActivity extends Activity {

    private static final String AES_KEY_NAME = "foobar";
    private static final int REQUEST_PERMISSION = 100;

    private View genKeyButton, encryptButton, decryptButton;
    private TextView tvLog;

    private KeyStore keyStore;
    private FingerprintManager fingerprintManager;
    private byte[] encrypted, IV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_mariotaku_finger);

        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e) {
            showErrorAndExit("Device doesn't support AndroidKeyStore");
            return;
        }
        fingerprintManager = getSystemService(FingerprintManager.class);

        genKeyButton = findViewById(R.id.btnMarioGenkey);
        encryptButton = findViewById(R.id.btnMarioEncrypt);
        decryptButton = findViewById(R.id.btnMarioDecrypt);
        tvLog = (TextView) findViewById(R.id.tvMarioLog);

        genKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                writeLog("Generating key......");
                try {
                    generateKey();
                    writeLog("[OK]");
                } catch (Exception e) {
                    writeError(e);
                }
                tvLog.append("\n");
            }
        });
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                writeLog("Encrypting data\n");
                try {
                    if (encryptData()) {
                        writeLog("Touch sensor......");
                    } else {
                        writeLog("[FAILED] key not yet generated\n");
                    }
                } catch (Exception e) {
                    writeError(e);
                }
            }
        });
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                writeLog("Decrypting data\n");
                try {
                    if (encrypted == null || IV == null) {
                        writeError("There's no encrypted data\n");
                    } else if (decryptData()) {
                        writeLog("Touch sensor......");
                    } else {
                        writeLog("[FAILED] key not yet generated\n");
                    }
                } catch (Exception e) {
                    writeError(e);
                }
            }
        });
        tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        // We should check permission on runtime in Marshmallow
        if (checkSelfPermission(Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            checkFingerprintAvailable();
        } else {
            final String[] permissions = {Manifest.permission.USE_FINGERPRINT};
            requestPermissions(permissions, REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkFingerprintAvailable();
                } else {
                    showErrorAndExit("Please give app fingerprint permission");
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void showErrorAndExit(final String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        finish();
    }


    @SuppressWarnings("ResourceType")
    private void checkFingerprintAvailable() {
        if (!fingerprintManager.isHardwareDetected()) {
            showErrorAndExit("This device doesn't support Fingerprint authentication");
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            showErrorAndExit("You haven't enrolled any fingerprint, go to System Settings -> Security -> Fingerprint");
        }
    }

    private void generateKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IOException, CertificateException {
        // Use AES algorithm. Here we must use AndroidKeyStore for provider.
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        // Reload our keystore
        keyStore.load(null);
        // We use this key to encrypt and decrypt data
        final int purposes = KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT;
        final KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(AES_KEY_NAME, purposes);
        // Require the user to authenticate with a fingerprint to authorize every use of the key
        builder.setUserAuthenticationRequired(true);

        builder.setBlockModes(KeyProperties.BLOCK_MODE_CBC);
        builder.setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
        keyGenerator.init(builder.build());
        keyGenerator.generateKey();
    }

    /**
     * @return false if key not yet generated
     */
    private boolean encryptData() throws CertificateException, NoSuchAlgorithmException, IOException,
            UnrecoverableKeyException, KeyStoreException, NoSuchPaddingException, InvalidKeyException,
            SecurityException {
        keyStore.load(null);
        // Load key from KeyStore
        final SecretKey key = (SecretKey) keyStore.getKey(AES_KEY_NAME, null);
        // The key is required, notify user to regenerate one.
        if (key == null) return false;
        // When using CBC block mode, an IV is required to decrypt data. Usually IV is generated
        // along with encrypted key.
        final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        // Use Encrypt mode.
        cipher.init(Cipher.ENCRYPT_MODE, key);
        final FingerprintManager.CryptoObject crypto = new FingerprintManager.CryptoObject(cipher);
        fingerprintManager.authenticate(crypto, null, 0, new SimpleAuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(final FingerprintManager.AuthenticationResult result) {
                final Cipher cipher = result.getCryptoObject().getCipher();
                String str = "szw is an android programmer";
                final byte[] data = str.getBytes();
                writeLog("[OK]\n");
                writeLog("---> (1) : "+str+"\n\n");
                try {
                    encrypted = cipher.doFinal(data);
                    // IV is required for decryption
                    IV = cipher.getIV();
                } catch (IllegalBlockSizeException | BadPaddingException e) {
                    writeError(e);
                }
            }

        }, new Handler());
        return true;
    }

    private boolean decryptData() throws CertificateException, NoSuchAlgorithmException, IOException,
            UnrecoverableKeyException, KeyStoreException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, SecurityException {
        keyStore.load(null);
        // Load key from KeyStore
        final SecretKey key = (SecretKey) keyStore.getKey(AES_KEY_NAME, null);
        // The key is required, notify user to regenerate one.
        if (key == null) return false;
        // When using CBC block mode, an IV is required to decrypt data. Usually IV is generated
        // along with encrypted key.
        final Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        // Use Decrypt mode
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV));
        final FingerprintManager.CryptoObject crypto = new FingerprintManager.CryptoObject(cipher);
        fingerprintManager.authenticate(null, null, 0, new SimpleAuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(final FingerprintManager.AuthenticationResult result) {
                try {
                    final Cipher cipher = result.getCryptoObject().getCipher();
                    writeLog("[OK]\n");

                    byte[] decrypted = cipher.doFinal(encrypted);
                    writeLog(" --> (2) : "+new String(decrypted)+" \n\n");
                } catch (Exception e) {
                    writeError(e);
                }
            }
        }, new Handler());
        return true;
    }


    private void writeLog(final CharSequence s) {
        tvLog.append(s);
    }

    private void writeError(final Exception e) {
        tvLog.append("[ERROR]\n");
        try (StringWriter sw = new StringWriter()) {
            e.printStackTrace(new PrintWriter(sw));
            tvLog.append(sw.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void writeError(final CharSequence s) {
        tvLog.append("[ERROR]\n");
        tvLog.append(s);
    }

    private class SimpleAuthenticationCallback extends FingerprintManager.AuthenticationCallback {
        @Override
        public void onAuthenticationError(final int errorCode, final CharSequence errString) {
            writeError(errString);
        }

        @Override
        public void onAuthenticationHelp(final int helpCode, final CharSequence helpString) {
            writeError(helpString);
        }

        @Override
        public void onAuthenticationFailed() {
            writeError("Couldn't recognize you\n");
        }

    }
}
