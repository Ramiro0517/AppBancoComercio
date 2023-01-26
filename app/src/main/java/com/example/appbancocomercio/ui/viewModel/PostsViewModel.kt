package com.example.appbancocomercio.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbancocomercio.data.model.PostsModel
import com.example.appbancocomercio.domain.GetPostsUseCase
import kotlinx.coroutines.launch


class PostsViewModel : ViewModel() {

    val postsModel = MutableLiveData<PostsModel>()
    var  getPostsUseCase = GetPostsUseCase()

    fun onCreate() {
          viewModelScope.launch {
              val result = getPostsUseCase()
              if (!result.isNullOrEmpty()){
                  postsModel.postValue(result[0])

              }
          }
    }



}