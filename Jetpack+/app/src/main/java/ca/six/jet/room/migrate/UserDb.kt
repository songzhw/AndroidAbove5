package ca.six.jet.room.migrate

import android.content.Context
import androidx.room.*


@Entity
class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val timestamp: Long
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User?)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>?
}


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

object UserDatabaseProvider {
    @Volatile
    private var db: UserDatabase? = null

    fun db(context: Context): UserDatabase = db ?: synchronized(this) {
        db ?: build(context).also { db = it }
    }

    private fun build(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "migrates.db"
        )
            .build()
}