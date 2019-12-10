package ca.six.jet.room.migrate

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Entity
class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val timestamp: Long,
    val weight: Int,
    val sex: String
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User?)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>?
}


@Database(entities = [User::class], version = 2)
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
            .addMigrations(MIGRATE_1_2)
            .build()
}

// v2: 新加了int weight,  String sex成员
val MIGRATE_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN weight INTEGER NOT NULL DEFAULT 0");
        database.execSQL("ALTER TABLE user ADD COLUMN sex TEXT");
    }
}

