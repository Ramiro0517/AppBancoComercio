package com.example.appbancocomercio.data.network

import com.example.appbancocomercio.data.model.PostsModel
import retrofit2.Response

import retrofit2.http.GET

interface PostsApiClient {

    @GET("users")
    suspend fun getAllPosts(): Response<List<PostsModel>>
}