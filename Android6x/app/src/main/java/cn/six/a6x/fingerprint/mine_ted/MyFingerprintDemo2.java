package cn.six.a6x.fingerprint.mine_ted;

import android.Manifest;
import android.app.Activity;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.six.a6x.R;
import others.ted_permission.PermissionListener;
import others.ted_permission.TedPermission;

/**
 * Created by songzhw on 2016/3/10
 */
public class MyFingerprintDemo2 extends Activity {
    private ImageView iv;
    private TextView tv;

    private FingerprintManager fingerprintMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fingerprint);
        iv = (ImageView) findViewById(R.id.ivMyFingerprint);
        tv = (TextView) findViewById(R.id.tvMyFingerprint);

        fingerprintMgr = getSystemService(FingerprintManager.class);

    }

    public void clickAuthen(View v){
        new TedPermission(this)
                .setPermissionListener(listener)
                .setPermissions(Manifest.permission.USE_FINGERPRINT)
                .check();
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            tv.setText("Start to authenticate your fingerprint ...");
            iv.setImageResource(R.drawable.ic_fingerprint);
            try {
                fingerprintMgr.authenticate(null, null, 0, fingerResult, null);
            } catch(SecurityException e){
                Toast.makeText(MyFingerprintDemo2.this, "fingerpirnt error : Permmision, hardward, or has no fingerprint", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            tv.setText("[Fail on permission!]" );
            iv.setImageResource(R.drawable.ic_fingerprint_error);
        }
    };


    protected  FingerprintManager.AuthenticationCallback fingerResult = new FingerprintManager.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence msg) {
            tv.setText("[Error]" + msg + " ;  Please try again");
            iv.setImageResource(R.drawable.ic_fingerprint_error);
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence msg) {
            tv.setText("[Help]" + msg);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            FingerprintManager.CryptoObject crypto = result.getCryptoObject();
            tv.setText("[Succ]" + crypto);
            iv.setImageResource(R.drawable.ic_fingerprint_success);
        }

        @Override
        public void onAuthenticationFailed() {
            tv.setText("[Fail]" );
            iv.setImageResource(R.drawable.ic_fingerprint_error);
        }
    };


}
