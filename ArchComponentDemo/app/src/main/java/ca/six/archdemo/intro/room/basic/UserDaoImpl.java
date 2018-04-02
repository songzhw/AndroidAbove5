package ca.six.archdemo.intro.room.basic;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import java.util.List;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> loadAllByIds(int[] userIds) {
        return null;
    }

    @Override
    public User findByName(String first, String last) {
        return null;
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Override
    public void insertAll(User... users) {

    }

    @Override
    public void delete(User user) {

    }
}
