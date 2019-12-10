package ca.six.jet.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

val MAN = 1
val WOMAN = 2

@Entity
data class Student(
    val name: String,
    val age: String,
    val sex: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        // 这个super.toString仍是"Student@内存地址"
        return "Student[$id, $name, age=$age, gender=$sex] "
    }
}

@Dao
interface StudentDao {
    @Query("select * from Student ")
    fun getStudents(): Flow<List<Student>>

    @Query("select * from Student where id = :id")
    suspend fun getStudentById(id: Int): Student?

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