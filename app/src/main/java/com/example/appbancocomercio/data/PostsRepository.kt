package com.example.appbancocomercio.data

import com.example.appbancocomercio.data.model.PostsModel
import com.example.appbancocomercio.data.model.PostsProvider
import com.example.appbancocomercio.data.network.PostsService

class PostsRepository {

    private val api = PostsService()

    suspend fun getAllPosts():List<PostsModel>{
        val response = api.getPosts()
        PostsProvider.posts = response
        return response
    }
}