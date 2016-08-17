package cn.six.a6x.fingerprint.mine;

import android.app.Activity;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.six.a6x.R;

/**
 * Created by songzhw on 2015/12/25
 */
public class MyFingerprintTestActivity extends FingerpringBaseActivity {
    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fingerprint);

        iv = (ImageView) findViewById(R.id.ivMyFingerprint);
        tv = (TextView) findViewById(R.id.tvMyFingerprint);

    }

    public void clickAuthen(View v){
        boolean isFingerprintEnable = isFingerprintAvailable();
        if(isFingerprintEnable){
            tv.setText("Start to authenticate your fingerprint ...");
            iv.setImageResource(R.drawable.ic_fingerprint);
            authenFingerprint();
        }
    }

    // Touch the fingerprint zone will get no response
    // msg is like "尝试次数过多， 请稍后重试 "
    @Override
    public void onAuthenError(CharSequence msg) {
        tv.setText("[Error]" + msg + " ;  Please try again");
        iv.setImageResource(R.drawable.ic_fingerprint_error);
    }


    // After getting this, you can retry and touch the fingerprint zone again.
    @Override
    public void onAuthenFail() {
        tv.setText("[Fail]" );
        iv.setImageResource(R.drawable.ic_fingerprint_error);
    }

    @Override
    public void onAuthenHelp(CharSequence msg) {
        tv.setText("[Help]" + msg);
    }

    @Override
    public void onAuthenSucc(FingerprintManager.AuthenticationResult result) {
        FingerprintManager.CryptoObject crypto = result.getCryptoObject();
        tv.setText("[Succ]" + crypto);
        iv.setImageResource(R.drawable.ic_fingerprint_success);
    }
}
