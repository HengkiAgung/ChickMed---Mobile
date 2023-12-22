package bangkit.product.chickmed.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleModel(
    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    val id: Int = 0,
    @field:ColumnInfo(name = "title")
    val title: String,
    @field:ColumnInfo(name = "time")
    val time: String,
    @field:ColumnInfo(name = "day")
    val day: String,
    @field:ColumnInfo(name = "isActive")
    val isActive: Boolean,
)