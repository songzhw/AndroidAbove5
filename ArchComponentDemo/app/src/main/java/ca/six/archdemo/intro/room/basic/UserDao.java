package ca.six.archdemo.intro.room.basic;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    // 传递数组, List时, 用 (:**) 来传递
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE uname LIKE :name LIMIT 1")
    User findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE uid IN (:uids)")
    LiveData<List<User>> loadAllByIds(List<Integer> uids);

    @Query("SELECT * FROM user WHERE birthday BETWEEN :from AND :to")
    List<User> findUserBornIn(Date from, Date to);

}
