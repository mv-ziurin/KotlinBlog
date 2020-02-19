package com.example.kotlinblog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinblog.database.blog.PostDao
import com.example.kotlinblog.model.blog.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}