package ca.six.jet.room.relation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import ca.six.jet.R
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.launch

class RecipeDbDemo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val db = RecipeDatabaseProvider.db(this)
        println("szw db2 : ${db}")
        val dao = db.dao()

        val vm = ViewModelProviders.of(this).get(RecipeRoomViewModel::class.java)

        btnInsert.setOnClickListener { vm.insert(dao) }
        btnQueryAll.setOnClickListener {
            vm.query(dao)
        }

    }
}

class RecipeRoomViewModel : ViewModel() {

    fun insert(dao: RecipeDao){
//        viewModelScope.launch {
//            dao.insertCuisine(Cuisine(null, "西红柿炒蛋"))
//            dao.insertCuisine(Cuisine(null, "牛肉滑蛋"))
//            dao.insertCuisine(Cuisine(null, "青椒小炒牛肉"))
//            dao.insertCuisine(Cuisine(null, "西红柿炖牛腩"))
//
//            dao.insertIngredient(Ingredient(null, "鸡蛋"))
//            dao.insertIngredient(Ingredient(null, "西红柿"))
//            dao.insertIngredient(Ingredient(null, "牛肉"))
//            dao.insertIngredient(Ingredient(null, "辣椒"))
//
//            dao.insertJoint(IngredientCuisine(null, 1, 1))
//            dao.insertJoint(IngredientCuisine(null, 2, 1))
//            dao.insertJoint(IngredientCuisine(null, 1, 2))
//            dao.insertJoint(IngredientCuisine(null, 3, 2))
//            dao.insertJoint(IngredientCuisine(null, 4, 3))
//            dao.insertJoint(IngredientCuisine(null, 3, 3))
//            dao.insertJoint(IngredientCuisine(null, 2, 4))
//            dao.insertJoint(IngredientCuisine(null, 3, 4))
//            dao.insertJoint(IngredientCuisine(null, 4, 4))
//        }
    }

    fun query(dao: RecipeDao) {
        viewModelScope.launch {
            val result = dao.cuisines()
            println("szw " + result)
        }
    }
}