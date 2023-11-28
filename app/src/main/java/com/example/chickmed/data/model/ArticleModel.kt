package com.example.chickmed.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_article")
data class ArticleModel(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    val id: Int,
    @field:ColumnInfo(name = "title")
    val title: String,
    @field:ColumnInfo(name = "content")
    val content: String,
    @field:ColumnInfo(name = "image")
    val image: String,
    @field:ColumnInfo(name = "date")
    val date: String,
)