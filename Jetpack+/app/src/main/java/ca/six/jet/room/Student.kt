package ca.six.jet.room

import androidx.room.*

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: String,
    val sex: Int
)

@Dao
interface StudentDao{
    @Query("select * from Student where id = :id")
    fun getStudentById(id: String): Student?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    // 其实也可以用@Delete.  @Query其实就是executeSQL()的方式, 可以包罗万象.
    @Query("delete from Student")
    fun deleteAll()
}