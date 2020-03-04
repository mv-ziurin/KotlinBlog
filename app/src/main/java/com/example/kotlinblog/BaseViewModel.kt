package com.example.kotlinblog

import androidx.lifecycle.ViewModel
import com.example.kotlinblog.injection.component.DaggerViewModelInjector
import com.example.kotlinblog.injection.component.ViewModelInjector
import com.example.kotlinblog.injection.module.NetworkModule
import com.example.kotlinblog.ui.blog.PostListViewModel
import com.example.kotlinblog.ui.blog.PostViewModel
import com.example.kotlinblog.ui.news.ArticleListViewModel
import com.example.kotlinblog.ui.news.ArticleViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
            is ArticleListViewModel -> injector.inject(this)
            is ArticleViewModel -> injector.inject(this)
        }
    }
}