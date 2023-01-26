package com.example.appbancocomercio.domain

import com.example.appbancocomercio.data.model.PostsModel
import com.example.appbancocomercio.data.PostsRepository

class GetPostsUseCase () {

    private val repository = PostsRepository()
    suspend operator fun  invoke():List<PostsModel>?{
        return repository.getAllPosts()
    }
}