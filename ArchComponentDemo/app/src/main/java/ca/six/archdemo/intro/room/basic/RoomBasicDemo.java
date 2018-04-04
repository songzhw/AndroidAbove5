package ca.six.archdemo.intro.room.basic;

import android.app.Activity;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

        Migration migration1to2 = new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE User ADD COLUMN name TEXT");
                // database.execSQL("ALTER TABLE User DROP COLUMN last_name"); //不支持drop column
            }
        };
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbName")
                // .fallbackToDestructiveMigration() //旧表会被全丢弃. 请慎用
                .addMigrations(migration1to2) // 这里参数是一个migrate..., 可以加多个migrate
                .build();
        dao = db.userDao();
    }

    public void onClickSimpleButton(View v) {
        new Thread(){
            @Override
            public void run() {
                Address address = new Address("NY", 333);
                User user = new User(200, "Ross Gallar", address);
                dao.insertAll(user);
                System.out.println("szw insertion complete");
            }
        }.start();

    }

    public void onClickSimpleButton2(View v) {
        new Thread(){
            @Override
            public void run() {
                User user = dao.findByName("chandler bean");
                System.out.println("szw use = " + user);
            }
        }.start();

    }

}

// 若在主线程上操作, 如dao.insertAll(user),   会有:  java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
// 同样在主线程上取数据, 如dao.findByName(), 也会有:  java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.