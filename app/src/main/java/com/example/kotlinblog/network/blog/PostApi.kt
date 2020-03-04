package com.example.kotlinblog.network.blog

import com.example.kotlinblog.model.blog.Post
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the posts from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}