package ca.six.archdemo.intro.room.basic;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ca.six.archdemo.R;


public class RoomBasicDemo extends Activity {

    private TextView tv;
    private AppDatabase db;
    private UserDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbName")
                .build();
        dao = db.userDao();
    }

    public void onClickSimpleButton(View v) {
        User user = new User(100, "chandler", "bean");
        dao.insertAll(user);

    }

    public void onClickSimpleButton2(View v) {

    }

}