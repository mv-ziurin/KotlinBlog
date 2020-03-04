package com.example.kotlinblog.injection.module

import com.example.kotlinblog.network.news.NewsApi
import com.example.kotlinblog.network.blog.PostApi
import com.example.kotlinblog.util.BASE_URL_POSTS
import com.example.kotlinblog.util.TOP_HEADLINES_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Article service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Article service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideNewsApi(@Named("news") retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("news")
    internal fun provideRetrofitNewsInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TOP_HEADLINES_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(@Named("blog") retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("blog")
    internal fun provideRetrofitBlogInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_POSTS)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}