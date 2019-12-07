package ca.six.jet.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import ca.six.jet.R
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SimpleRoomDemo : AppCompatActivity() {
    lateinit var vm: SimpleRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val db = DatabaseProvider.db(this)
        val dao = db.studentDao()

        vm = ViewModelProviders.of(this).get(SimpleRoomViewModel::class.java)
        vm.liveStudents.observe(this, Observer { students ->
            val sb = StringBuilder()
            students.forEach { student ->
                sb.append(student)
                sb.append("\n")
            }
            tvInfo.text = sb.toString()
        })


        btnInsert.setOnClickListener {
            vm.insertValues(dao)
            tvInfo.text = "inserted"
        }

        btnQuery.setOnClickListener {
            vm.query(dao);
        }
    }
}

class SimpleRoomViewModel : ViewModel() {
    val liveStudents: MutableLiveData<List<Student>> = MutableLiveData<List<Student>>()

    fun insertValues(dao: StudentDao) {
        viewModelScope.launch {
            dao.insert(Student(null, "Jim", "1990-01-01", MAN))
            dao.insert(Student(null, "Mark", "1990-12-21", MAN))
            dao.insert(Student(null, "Kevin", "1990-05-20", MAN))
            dao.insert(Student(null, "Ali", "1990-08-06", MAN))
            dao.insert(Student(null, "Lily", "1991-12-01", WOMAN))
            dao.insert(Student(null, "Hellen", "1992-03-01", WOMAN))
        }
    }

    fun query(dao: StudentDao) {
        viewModelScope.launch {
            val result = dao.getStudents();
            result.collect { students ->
                liveStudents.postValue(students)
            }
        }
    }
}