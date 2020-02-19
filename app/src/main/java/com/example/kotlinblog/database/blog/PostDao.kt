package com.example.kotlinblog.database.blog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlinblog.model.blog.Post

@Dao
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg users: Post)
}