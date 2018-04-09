package ca.six.archdemo.intro.room.basic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import android.arch.persistence.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;
import java.util.List;

import ca.six.archdemo.BuildConfig;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AppDatabaseTest {
    private AppDatabase db;
    private UserDao dao;

    @Before
    public void setUp() {
        db = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, AppDatabase.class).build();
        dao = db.userDao();
    }

    @After
    public void cleanUp() {
        db.close();
    }

    @Test
    public void f1() {
        Thread thread = new Thread( () -> {
            System.out.println("szw1 Thread = " + Thread.currentThread().getName());
            User user = dao.findByName("jorden");
            assertNotNull(user);
        });
        thread.start();
    }



}