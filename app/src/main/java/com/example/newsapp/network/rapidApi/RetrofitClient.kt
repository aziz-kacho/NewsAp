package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://contextualwebsearch-websearch-v1.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val getNewsApi: NewsApi = retrofit.create(NewsApi::class.java)


}