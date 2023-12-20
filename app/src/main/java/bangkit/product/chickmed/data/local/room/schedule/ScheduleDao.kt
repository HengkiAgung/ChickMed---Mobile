package bangkit.product.chickmed.data.local.room.schedule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bangkit.product.chickmed.data.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getSchedule(): Flow<List<ScheduleModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchedule(schedule: ScheduleModel)

    @Query("DELETE FROM schedule WHERE id = :id")
    suspend fun deleteSchedule(id: Int)

    @Query("SELECT * FROM schedule WHERE id = :id")
    fun getScheduleById(id: Int): Flow<ScheduleModel>

    @Query("UPDATE schedule SET title = :title, time = :time, day = :day, isActive = :isActive WHERE id = :id")
    suspend fun updateSchedule(id: Int, title: String, time: String, day: String, isActive: Boolean)

    @Query("UPDATE schedule SET isActive = :isActive WHERE id = :id")
    suspend fun updateActiveSchedule(id: Int, isActive: Boolean)
}