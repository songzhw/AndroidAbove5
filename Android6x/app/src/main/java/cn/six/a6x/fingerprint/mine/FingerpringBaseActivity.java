package cn.six.a6x.fingerprint.mine;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by songzhw on 2015/12/25
 * Note: You can rewrite this code, like "extends YourBaseActivity"
 *
 *
 *  public methods :
 *     * onAuthenSucc()
 *     * isFingerprintAvailable()
 *
 */
public class FingerpringBaseActivity extends Activity {
    private static final int REQUEST_PERMISSION = 110;

    private FingerpringBaseActivity myself;
    private FingerprintManager fingerprintMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myself = this;
        fingerprintMgr = getSystemService(FingerprintManager.class); // getSystemService() : API 23

    }

    // ====================================================
    // 1. detect fingerprint

    protected boolean isFingerprintAvailable(){
        boolean hasPersmission = checkSelfPermission(Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED;
        boolean hasHardware = fingerprintMgr.isHardwareDetected();
        boolean hasFingerprints = fingerprintMgr.hasEnrolledFingerprints();
        if(hasPersmission && hasHardware && hasFingerprints){
            return true;
        } else {
            if(! hasPersmission){
                final String[] permissions = {Manifest.permission.USE_FINGERPRINT};
                requestPermissions(permissions, REQUEST_PERMISSION);
            } else if(!hasHardware){ // hasPermission && !hasHardware
                Toast.makeText(this, "This device doesn't support Fingerprint authentication", Toast.LENGTH_LONG).show();
            } else if(!hasFingerprints) { // hasPermssion && hasHardware && !hasFingerprints
                Toast.makeText(this, "You haven't enrolled any fingerprint, go to System Settings -> Security -> Fingerprint", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isFingerprintAvailable();
                } else {
                    Toast.makeText(this, "Please give app fingerprint permission", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }




    // ====================================================
    // 2. Fingerprint Result

    public void onAuthenError(CharSequence msg){

    }

    public void onAuthenFail(){

    }

    public void onAuthenHelp(CharSequence msg){

    }

    public void onAuthenSucc(FingerprintManager.AuthenticationResult result){

    }

    public void authenFingerprint(){
        try {
            fingerprintMgr.authenticate(null, null, 0, fingerResult, null);
        } catch(SecurityException e){
            Toast.makeText(this, "fingerpirnt error : Permmision, hardward, or has no fingerprint", Toast.LENGTH_LONG).show();
        }
    }


    protected  FingerprintManager.AuthenticationCallback fingerResult = new FingerprintManager.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            myself.onAuthenError(errString);
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            myself.onAuthenHelp(helpString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            myself.onAuthenSucc(result);
        }

        @Override
        public void onAuthenticationFailed() {
            myself.onAuthenFail();
        }
    };


}
