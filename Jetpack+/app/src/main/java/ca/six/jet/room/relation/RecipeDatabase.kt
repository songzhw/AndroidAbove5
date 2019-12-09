package ca.six.jet.room.relation

import android.content.Context
import android.os.AsyncTask
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val ingredientId: Int?,
    val name: String?
)

@Entity
data class Cuisine(
    @PrimaryKey(autoGenerate = true) val cuisineId: Int?,
    val name: String?
)

@Entity
data class IngredientCuisine(
    @PrimaryKey(autoGenerate = true) val icID: Int?,
    @ForeignKey(
        entity = Ingredient::class,
        parentColumns = ["ingredientId"],
        childColumns = ["ingredientId"]
    )
    val ingredientId: Int?,
    @ForeignKey(
        entity = Cuisine::class,
        parentColumns = ["cuisineId"],
        childColumns = ["cuisineId"]
    )
    val cuisineId: Int?
)


data class CuisineWithIngredients(
    @Embedded val cuisine: Cuisine,
    @Relation(
        parentColumn = "cuisineId",
        entity = Ingredient::class,
        entityColumn = "ingredientId",
        associateBy = Junction(IngredientCuisine::class)
    )
    val ingredients: List<Ingredient>
)

@Dao
interface RecipeDao {
    @Transaction
    @Query("select * from Cuisine")
    suspend fun cuisines(): List<CuisineWithIngredients>

    @Transaction
    @Insert
    suspend fun insertCuisine(cusine: Cuisine)

    @Transaction
    @Insert
    suspend fun insertIngredient(ingredient: Ingredient)

    @Transaction
    @Insert
    suspend fun insertJoint(join: IngredientCuisine)

}

@Database(
    entities = [Ingredient::class, Cuisine::class, IngredientCuisine::class],
    version = 1, exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun dao(): RecipeDao
}

object RecipeDatabaseProvider {
    // 因为需要一个context参数, 无法使用hungry式的单例. 所以我们就要注意@Volatile以及双加锁了
    @Volatile
    private var db: RecipeDatabase? = null

    fun db(context: Context): RecipeDatabase = db ?: synchronized(this) {
        db ?: build(context).also { db = it }
    }

    private fun build(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "recipes.db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    GlobalScope.launch {
                        println("szw RecipeDatabase create db")
                        val dao = db(context).dao()
                        dao.insertCuisine(Cuisine(null, "西红柿炒蛋"))
                        dao.insertCuisine(Cuisine(null, "牛肉滑蛋"))
                        dao.insertCuisine(Cuisine(null, "青椒小炒牛肉"))
                        dao.insertCuisine(Cuisine(null, "西红柿炖牛腩"))

                        dao.insertIngredient(Ingredient(null, "鸡蛋"))
                        dao.insertIngredient(Ingredient(null, "西红柿"))
                        dao.insertIngredient(Ingredient(null, "牛肉"))
                        dao.insertIngredient(Ingredient(null, "辣椒"))

                        dao.insertJoint(IngredientCuisine(null, 1, 1))
                        dao.insertJoint(IngredientCuisine(null, 2, 1))
                        dao.insertJoint(IngredientCuisine(null, 1, 2))
                        dao.insertJoint(IngredientCuisine(null, 3, 2))
                        dao.insertJoint(IngredientCuisine(null, 4, 3))
                        dao.insertJoint(IngredientCuisine(null, 3, 3))
                        dao.insertJoint(IngredientCuisine(null, 2, 4))
                        dao.insertJoint(IngredientCuisine(null, 3, 4))
                        dao.insertJoint(IngredientCuisine(null, 4, 4))
                    }
                }
            })
            .build()

}