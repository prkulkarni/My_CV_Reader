package com.prasad.cvreader.network

import io.reactivex.Observable
import com.prasad.cvreader.model.Post
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/prkulkarni/9ada52f269473a360e3b71c28446710a/raw/87f186c0bfacb30067aec732d2730aab43986314/sample_cv.json")
    fun getPosts(): Observable<Post>
}