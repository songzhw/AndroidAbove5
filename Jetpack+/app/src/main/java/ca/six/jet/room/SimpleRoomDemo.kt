package ca.six.jet.room

import android.app.Activity
import android.os.Bundle
import ca.six.jet.R
import kotlinx.android.synthetic.main.activity_room.*

class SimpleRoomDemo : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        btnInsert.setOnClickListener {
            val db = DatabaseProvider.db(this)
            val dao = db.studentDao()
            dao.insert(Student(null, "Jim", "1990-01-01", MAN))
            dao.insert(Student(null, "Mark", "1990-12-21", MAN))
            dao.insert(Student(null, "Kevin", "1990-05-20", MAN))
            dao.insert(Student(null, "Ali", "1990-08-06", MAN))
            dao.insert(Student(null, "Lily", "1991-12-01", WOMAN))
            dao.insert(Student(null, "Hellen", "1992-03-01", WOMAN))
            tvInfo.text = "inserted"
        }
    }
}