package com.example.chickmed.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chickmed.data.model.ArticleModel

@Database(entities = [ArticleModel::class], version = 1, exportSchema = false)
abstract class BookmarkArticleDatabase : RoomDatabase() {
    abstract fun bookmarkArticleDao(): BookmarkArticleDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkArticleDatabase? = null

        fun getInstance(context: Context): BookmarkArticleDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkArticleDatabase::class.java, "bookmark_article"
                ).build()
            }
    }
}