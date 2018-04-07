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

import java.util.Date;
import java.util.List;

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
                // 旧表是: uid, first_name, last_name,  adds_city, adds_postCode
                // 新表是: uid, uname,                  adds_city, adds_postCode

                database.execSQL("CREATE TABLE usr_bak (uid INTEGER PRIMARY KEY NOT NULL, uname TEXT, adds_city TEXT, adds_postCode INTEGER)");
                database.execSQL("INSERT INTO usr_bak (uid, uname, adds_city, adds_postCode) SELECT uid, first_name, adds_city, adds_postCode FROM User");
                database.execSQL("DROP TABLE User");
                database.execSQL("ALTER TABLE usr_bak RENAME TO User");
            }
        };

        Migration migration2to3 = new Migration(2, 3) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE User ADD COLUMN birthday INTEGER");
            }
        };

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dbName")
                .addMigrations(migration1to2, migration2to3)
                .build();
        dao = db.userDao();
    }

    public void onClickSimpleButton(View v) {
        new Thread(){
            @Override
            public void run() {
                Date birthday = new Date(1984, 01, 01);
                Address address = new Address("NewYork", 488);
                User user = new User(300, "Rachel Green", address, birthday);
                dao.insertAll(user);
                System.out.println("szw insertion complete");
            }
        }.start();

    }

    public void onClickSimpleButton2(View v) {
        new Thread(){
            @Override
            public void run() {
                Date from = new Date(1980, 1, 1);
                Date to = new Date(1989, 12, 31);
                List<User> users = dao.findUserBornIn(from, to);

                for(User user: users){
                    System.out.println("szw find user : "+user);
                }
            }
        }.start();

    }

}

// 若在主线程上操作, 如dao.insertAll(user),   会有:  java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
// 同样在主线程上取数据, 如dao.findByName(), 也会有:  java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.