package com.example.newsapp.api

import com.example.newsapp.data.Models.RapidApiNews.GetNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

    @GET("api/search/TrendingNewsAPI")
    fun getListOfNews(
        @Query("pageNumber")
        pageNumber: Int,

        @Query("pageSize")
        pageSize: Int = 10,

        @Query("withThumbnails")
        withThumbnails: Boolean = false,

        @Query("location")
        location: String = "us",

        @Header("x-rapidapi-key")
        apiKey: String = "4f9fec66aemsh2c7d98e8d3b70a1p1a61bdjsn0df841835fda",

        @Header("x-rapidapi-host")
        host: String = "contextualwebsearch-websearch-v1.p.rapidapi.com",

        @Header("useQueryString")
        useQueryString : Boolean = true

//        .addHeader("x-rapidapi-key","4f9fec66aemsh2c7d98e8d3b70a1p1a61bdjsn0df841835fda")
//    .addHeader("x-rapidapi-host","contextualwebsearch-websearch-v1.p.rapidapi.com")
//    .addHeader("useQueryString","true")

    ): Call<GetNews>
}