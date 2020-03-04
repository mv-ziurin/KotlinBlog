package com.example.kotlinblog.network.news

import com.example.kotlinblog.model.news.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)