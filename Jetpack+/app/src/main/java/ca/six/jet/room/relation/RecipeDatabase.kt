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
    val name: String
)

@Entity
data class Cuisine(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String
)

@Entity
data class IngredientCuisine(
    val ingredientId: Int,
    val cuisineId: Int
)

@Entity
data class CuisineWithIngredients(
    @Embedded val cusine: Cuisine,
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
    fun cuisines(): List<CuisineWithIngredients>
}

@Database(entities = [Ingredient::class, Cuisine::class, IngredientCuisine::class],
    version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase(){
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
            "recipe.db"
        )
            .build()
}