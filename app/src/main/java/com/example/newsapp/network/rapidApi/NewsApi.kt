package com.example.newsapp.api

import com.example.newsapp.data.Models.RapidApiNews.GetNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("api/search/TrendingNewsAPI")
    fun getListOfNews(
        @Query("pageNumber")
        pageNumber: Int,

        @Query("pageSize")
        pageSize: Int,

        @Query("withThumbnails")
        withThumbnails: Boolean,

        @Query("location")
        location: String
    ): Call<GetNews>
}