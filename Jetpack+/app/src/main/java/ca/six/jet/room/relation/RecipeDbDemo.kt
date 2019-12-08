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
        val dao = db.dao()

        val vm = ViewModelProviders.of(this).get(RecipeRoomViewModel::class.java)
        btnQueryAll.setOnClickListener {
            vm.query(dao)
        }

    }
}

class RecipeRoomViewModel : ViewModel() {

    fun query(dao: RecipeDao) {
        viewModelScope.launch {
            val result = dao.cuisines()
            println("szw " + result)
        }
    }
}