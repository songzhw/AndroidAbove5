package ca.six.jet.room.same_name

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.room.*
import kotlinx.coroutines.launch

@Entity
data class Sofa(@PrimaryKey val id: Int)

@Entity
data class Pillow(@PrimaryKey val id: Int, val sofaId: Int)

data class SofaPillow(@Embedded val sofa: Sofa, @Embedded val pillow: Pillow)

@Dao
interface SofaDao {
//    @Query("select Sofa.*, Pillow.*  from Sofa inner join Pillow on Pillow.sofaId = Sofa.id")
//    suspend fun getAll(): List<SofaPillow>

    @Query("select * from Sofa")
    suspend fun getSofas(): List<Sofa>

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


class SofaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = SofaDatabaseProvider.db(this).dao()

        val vm = ViewModelProviders.of(this).get(SofaViewModel::class.java)
        vm.init(dao)

        vm.data.observe(this, Observer {
            println("szw sofas = $it")
        })


    }
}

class SofaViewModel : ViewModel() {
    lateinit var data: LiveData<List<Sofa>>
    fun init(dao: SofaDao) {
        viewModelScope.launch {
            //            dao.insertSofa(Sofa(10))
//            dao.insertSofa(Sofa(20))
//            dao.insertPillow(Pillow(11, 10))
//            dao.insertPillow(Pillow(12, 10))
//            dao.insertPillow(Pillow(21, 20))

            data = dao.getSofas()
        }
    }
}