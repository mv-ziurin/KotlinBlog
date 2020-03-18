package com.example.kotlinblog.ui.news

import androidx.lifecycle.MutableLiveData
import com.example.kotlinblog.BaseViewModel
import com.example.kotlinblog.model.news.Article

class ArticleViewModel : BaseViewModel() {
    private val articleTitle = MutableLiveData<String>()
    private val articleBody = MutableLiveData<String>()
    private val articleImageUrl = MutableLiveData<String>()

    fun bind(article: Article) {
        articleTitle.value = article.title
        articleBody.value = article.description
        articleImageUrl.value = article.urlToImage
    }

    fun getArticleTitle(): MutableLiveData<String> {
        return articleTitle
    }

    fun getArticleBody(): MutableLiveData<String> {
        return articleBody
    }

    fun getArticleImageUrl(): MutableLiveData<String> {
        return articleImageUrl
    }
}