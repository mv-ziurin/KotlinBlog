package com.example.kotlinblog.model.blog

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)