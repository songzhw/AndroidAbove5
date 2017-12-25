package cn.six.sup.ctlay.placeholder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Placeholder;
import android.view.View;

import cn.six.sup.R;


public class PlaceHolderDemo extends Activity implements View.OnClickListener {
    private Placeholder placeholderButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_holder_demo);

        placeholderButton = findViewById(R.id.template_action_button);

        findViewById(R.id.ib_save).setOnClickListener(this);
        findViewById(R.id.ib_edit).setOnClickListener(this);
        findViewById(R.id.ib_delete).setOnClickListener(this);
        findViewById(R.id.ib_cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        placeholderButton.setContentId(id);
    }
}
