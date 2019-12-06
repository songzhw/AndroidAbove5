package ca.six.jet.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object DatabaseProvider {
    // 因为需要一个context参数, 无法使用hungry式的单例. 所以我们就要注意@Volatile以及双加锁了
    @Volatile
    private var db: MyDatabase? = null

    fun db(context: Context): MyDatabase = db ?: synchronized(this) {
        db ?: build(context).also { db = it }
    }

    private fun build(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "room1.db"
        )
            .build()
}