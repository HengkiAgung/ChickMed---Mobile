package com.example.chickmed.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class ArticleResponse {
    data class StoryResponse(
        @field:SerializedName("listStory")
        val listStory: List<StoryItem>,

        @field:SerializedName("error")
        val error: Boolean,

        @field:SerializedName("message")
        val message: String
    )

    @Entity(tableName = "story")
    data class StoryItem(
        @PrimaryKey
        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("photoUrl")
        val photoUrl: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("description")
        val description: String,

        @field:SerializedName("lat")
        val lat: String? = null,

        @field:SerializedName("lon")
        val lon: String? = null,
    )
}