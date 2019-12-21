package ca.six.jet.room.same_name

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Query

@Entity
data class Sofa(val id: Int)

@Entity
data class pillow(val id: Int, val sofaId: Int)

@Dao
interface SofaDao {
}