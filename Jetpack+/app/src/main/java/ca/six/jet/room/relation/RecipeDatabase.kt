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
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String?
)

@Entity
data class Cuisine(
    @PrimaryKey(autoGenerate = true) val id: Int?,
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
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ForeignKey(entity = Ingredient::class, parentColumns = ["id"], childColumns = ["ingredientId"])
    val ingredientId: Int?,
    @ForeignKey(entity = Cuisine::class, parentColumns = ["id"], childColumns = ["cuisineId"])
    val cuisineId: Int?
)


data class CuisineWithIngredients(
    @Embedded val cuisine: Cuisine,
    @Relation(
        parentColumn = "id",
        entity = Ingredient::class,
        entityColumn = "id",
        associateBy = Junction(IngredientCuisine::class)
    )
    val ingredients: List<Ingredient>
)

@Dao
interface RecipeDao {
    @Transaction
    @Query("select * from Cuisine")
    suspend fun cuisines(): List<CuisineWithIngredients> //数据不对
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
            .createFromAsset("databases/recipes.db")
            .build()
}