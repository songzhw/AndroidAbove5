package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {User.class}, version = 2)
@TypeConverters({DbConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}


