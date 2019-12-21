package ca.six.jet.room.same_name

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.room.*
import kotlinx.coroutines.launch

@Entity
data class Sofa(val id: Int)

@Entity
data class Pillow(val id: Int, val sofaId: Int)

data class SofaPillow(@Embedded val sofa: Sofa, @Embedded val pillow: Pillow)

@Dao
interface SofaDao {
    @Query("select Sofa.id, Pillow.id from Sofa inner join Pillow on Pillow.sofaId = Sofa.id")
    suspend fun getAll(): LiveData<List<SofaPillow>>

    @Insert
    suspend fun insertSofa(sofa: Sofa)

    @Insert
    suspend fun insertPillow(pillow: Pillow)
}

@Database(
    entities = [Sofa::class, Pillow::class],
    version = 1, exportSchema = false
)
abstract class SofaDatabase : RoomDatabase() {
    abstract fun dao(): SofaDao
}


object SofaDatabaseProvider {
    @Volatile
    private var db: SofaDatabase? = null

    fun db(context: Context): SofaDatabase = db ?: synchronized(this) {
        db ?: build(context).also { db = it }
    }

    private fun build(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            SofaDatabase::class.java,
            "Sofas.db"
        )
            .build()
}
