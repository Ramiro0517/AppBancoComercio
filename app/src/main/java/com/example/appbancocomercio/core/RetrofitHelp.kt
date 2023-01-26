package com.example.appbancocomercio.core

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelp {

    private const val POSTS ="https://jsonplaceholder.typicode.com/"

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(POSTS)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}