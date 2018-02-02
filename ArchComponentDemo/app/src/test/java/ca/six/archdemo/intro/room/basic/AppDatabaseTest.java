package ca.six.archdemo.intro.room.basic;

import static junit.framework.Assert.assertNull;

import android.arch.persistence.room.Room;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricTestRunner.class)
public class AppDatabaseTest {
    private AppDatabase db;

    @Before
    public void setUp() {
        db = Room.databaseBuilder(RuntimeEnvironment.application, AppDatabase.class, "db_name").build();
    }

    @Test
    public void f1() {
        User user  = db.userDao().findByName("mike", "jorden");
        assertNull(user);
    }
}