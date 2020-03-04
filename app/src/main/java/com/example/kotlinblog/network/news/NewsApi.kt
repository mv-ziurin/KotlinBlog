package com.example.kotlinblog.network.news

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface NewsApi {
    /**
     * Get the list of the articles from the API
     */
    @GET("/v2/top-headlines?country=us&apiKey=2159dff3238f4299b65a8c3987216e7d")
    fun getArticles(): Observable<NewsResponse>
}