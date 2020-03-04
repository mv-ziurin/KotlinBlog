package com.example.kotlinblog.injection.component

import com.example.kotlinblog.injection.module.NetworkModule
import com.example.kotlinblog.ui.blog.PostListViewModel
import com.example.kotlinblog.ui.blog.PostViewModel
import com.example.kotlinblog.ui.news.ArticleListViewModel
import com.example.kotlinblog.ui.news.ArticleViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)

    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(postViewModel: PostViewModel)

    /**
     * Injects required dependencies into the specified ArticleListViewModel.
     * @param articleListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(articleListViewModel: ArticleListViewModel)

    /**
     * Injects required dependencies into the specified ArticleViewModel.
     * @param articleViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(articleViewModel: ArticleViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}