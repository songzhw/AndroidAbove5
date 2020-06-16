package ca.six.jet.room.migrate

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import org.jetbrains.annotations.NotNull


@Entity
class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @NotNull val name: String,
    val weight: String,
    val sex: String
)

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User?)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>?
}


@Database(entities = [User::class], version = 3)
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
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
}

// v2: 新加了int weight,  String sex成员
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN weight INTEGER NOT NULL DEFAULT 0");
        database.execSQL("ALTER TABLE user ADD COLUMN sex TEXT");
    }
}

// v3: name加个@NotNull, 去除timestamp字段, weight也由int变为了String
var MIGRATION_2_3: Migration? = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) { //1.创建一个新的符合Entity字段的新表user_new
        database.execSQL(
            "CREATE TABLE user_new ("
                    + "id INTEGER NOT NULL,"
                    + "name TEXT NOT NULL,"
                    + "age INTEGER NOT NULL,"
                    + "weight TEXT,"
                    + "sex TEXT,"
                    + "PRIMARY KEY(id))"
        )
        //2.将旧表user中的数据拷贝到新表user_new中
        database.execSQL(
            "INSERT INTO user_new(id,name,age,weight,sex) "
                    + "SELECT id,name,age,weight,sex FROM user"
        )
        //3.删除旧表user
        database.execSQL("DROP TABLE user")
        //4.将新表user_new重命名为user,升级完毕
        database.execSQL("ALTER TABLE user_new RENAME TO user")
    }
}