package ca.six.jet.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

val MAN = 1
val WOMAN = 2

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val age: String,
    val sex: Int
)

@Dao
interface StudentDao{
    @Query("select * from Student ")
    fun getStudents(): Flow<List<Student>>

    @Query("select * from Student where id = :id")
    fun getStudentById(id: Int): Student?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    // 其实也可以用@Delete.  @Query其实就是executeSQL()的方式, 可以包罗万象.
    @Query("delete from Student")
    suspend fun deleteAll()
}