package ca.six.demo.ctlay.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ca.six.demo.R;


public class GoneMarginDemo extends AppCompatActivity {

    private Button btnA;
    private Button btnB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctlay_margin);

        btnB = findViewById(R.id.btnB);
        btnA = findViewById(R.id.btnA);

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnA.getVisibility() == View.GONE){
                    btnA.setVisibility(View.VISIBLE);
                } else {
                    btnA.setVisibility(View.GONE);
                }
            }
        });

    }
}
