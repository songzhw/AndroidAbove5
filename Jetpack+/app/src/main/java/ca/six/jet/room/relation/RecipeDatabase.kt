package ca.six.jet.room.relation

import android.content.Context
import androidx.room.*

/*
CREATE TABLE IF NOT EXISTS ingredient (id INTEGER PRIMARY KEY AUTOINCREMENT, name text);
CREATE TABLE IF NOT EXISTS cuisine (id INTEGER PRIMARY KEY AUTOINCREMENT, name text);
CREATE TABLE IF NOT EXISTS ingredient_cuisine (
  ingredientId INTEGER,
  cuisineId INTEGER,
  FOREIGN KEY(ingredientId) REFERENCES ingredient(id),
  FOREIGN KEY(cuisineId) REFERENCES cuisine(id)
);
 */

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

/*
foreignKeys=[ForeignKey{referenceTable='Ingredient',
onDelete='NO ACTION',
onUpdate='NO ACTION',
columnNames=[IngredientId],
referenceColumnNames=[id]},
 */
@Entity
data class IngredientCuisine(
    @PrimaryKey(autoGenerate = true) val icID: Int?,
    @ForeignKey(entity = Ingredient::class, parentColumns = ["ingredientId"], childColumns = ["ingredientId"])
    val ingredientId: Int?,
    @ForeignKey(entity = Cuisine::class, parentColumns = ["cuisineId"], childColumns = ["cuisineId"])
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
    suspend fun cuisines(): List<CuisineWithIngredients> //数据不对

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
//            .createFromAsset("databases/recipes.db")
            .build()
}