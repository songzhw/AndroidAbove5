package cn.six.sup.other;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import cn.six.sup.R;

/**
 * Created by songzhw on 2017-03-19
 */

public class BottomSheetDemo extends Activity implements View.OnClickListener {

    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);

        dialog = new BottomSheetDialog(this);
    }

    public void onClickSimpleButton(View v){
        View view = getLayoutInflater().inflate(R.layout.view_bottom_menu, null);
        view.findViewById(R.id.tvBottomMenuCall).setOnClickListener(this);
        view.findViewById(R.id.tvBottomMenuPhoto).setOnClickListener(this);
        view.findViewById(R.id.tvBottomMenuGallery).setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();

    }

    public void onClickSimpleButton2(View v){
        dialog.dismiss();//用不上啊。因为dialog出来时， 点半透明的背景就可以让dialog给消失
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvBottomMenuCall:
                System.out.println("szw call");
                break;
            case R.id.tvBottomMenuPhoto:
                System.out.println("szw take photo");
                break;
            case R.id.tvBottomMenuGallery:
                System.out.println("szw gallery");
                break;
            default:
                break;
        }
    }
}
