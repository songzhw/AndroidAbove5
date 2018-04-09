package ca.six.archdemo.intro.room.basic;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import android.arch.persistence.room.Room;

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

    @Test
    public void f1() {
        new Thread( () -> {
            User user  = dao.findByName("jorden");
            assertNull(user);
        }).start();

    }

    @Test
    public void f2(){
        new Thread( () -> {
            dao.insertAll(new User(100, "jim", new Address("ny",100), new Date(1900, 1, 1)));
            dao.insertAll(new User(200, "lee", new Address("ny",200), new Date(1910, 1, 1)));
            dao.insertAll(new User(300, "kim", new Address("ny",300), new Date(1980, 1, 1)));

            List<User> oldUsers = dao.findUserBornIn(new Date(1900, 1,1), new Date(1920, 12, 31));
            assertEquals(3, oldUsers.size());
        }).start();
    }
}