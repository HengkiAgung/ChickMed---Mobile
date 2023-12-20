package bangkit.product.chickmed.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookmark_article")
data class ArticleModel(
    @field:PrimaryKey
    @field:ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @field:ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String,

    @field:ColumnInfo(name = "content")
    @field:SerializedName("content")
    val content: String,

    @field:ColumnInfo(name = "image")
    @field:SerializedName("image")
    val image: String,

    @field:ColumnInfo(name = "date")
    @field:SerializedName("date")
    val date: String,
)