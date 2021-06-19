package com.example.newsapp.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tj.livo.newsapp.models.NewsResponseSave

interface Api {
    @GET("v2/top-headlines")
    fun getListOfHeadLine(
        @Query("category") category: String,
        @Query("country") county: String = "us",
        @Query("apiKey") apiKey: String = "472e3f34fec24e96b91d0a4bcfe6887b",
    ): Call<NewsResponseSave>

}