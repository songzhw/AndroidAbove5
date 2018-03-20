package cn.six.a6x.fingerprint.boolret;

import android.app.Activity;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.six.a6x.R;

/**
 * Created by songzhw on 2015/12/24
 */
public class BoolFingerActivity extends Activity {
    private static final int REQUEST_PERMISSION = 110;
    private FingerprintManager fingerprintMgr;
    private Button btnPress;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_mariotaku_finger);
        fingerprintMgr = getSystemService(FingerprintManager.class);

        btnPress = (Button) findViewById(R.id.btnMarioDecrypt);
        tvLog = (TextView) findViewById(R.id.tvMarioLog);

/* TODO bring it back in production
        // We should check permission on runtime in Marshmallow
        if (checkSelfPermission(Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            if (!fingerprintMgr.isHardwareDetected()) {
                tvLog.setText("This device doesn't support Fingerprint authentication\n\n");
            } else if (!fingerprintMgr.hasEnrolledFingerprints()) {
                tvLog.setText("You haven't enrolled any fingerprint, go to System Settings -> Security -> Fingerprint\n\n");
            }
        } else {
            final String[] permissions = {Manifest.permission.USE_FINGERPRINT};
            requestPermissions(permissions, REQUEST_PERMISSION);
        }
*/

        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fingerprintMgr.authenticate(null, null, 0, authenticator, null);
                tvLog.setText("Press your finger !");
            }
        });
    }

    private FingerprintManager.AuthenticationCallback authenticator = new FingerprintManager.AuthenticationCallback() {
        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            tvLog.setText("FINGERPRINT --> onAuthenticationSucceeded()\n\n");
        }

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            tvLog.setText("FINGERPRINT --> onAuthenticationError() : " + errString + "\n\n");
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            tvLog.setText("FINGERPRINT --> onAuthenticationHelp() : " + helpString + "\n\n");
        }

        @Override
        public void onAuthenticationFailed() {
            tvLog.setText("FINGERPRINT --> onAuthenticationFailed()\n\n");
        }
    };
}
