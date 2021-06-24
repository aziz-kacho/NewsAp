package com.example.newsapp.network.searchRapidApi

import com.example.newsapp.data.Models.RapidApiNews.GetNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchRapidApi {


    @GET("api/Search/WebSearchAPI")
    fun getListOfNews(

        @Query("q")
        q: String,

        @Query("pageNumber")
        pageNumber: Int,

        @Query("pageSize")
        pageSize: Int = 10,

        @Query("autoCorrect")
        autoCorrect: Boolean = true,


        @Header("x-rapidapi-key")
        apiKey: String = "4f9fec66aemsh2c7d98e8d3b70a1p1a61bdjsn0df841835fda",

        @Header("x-rapidapi-host")
        host: String = "contextualwebsearch-websearch-v1.p.rapidapi.com",

        @Header("useQueryString")
        useQueryString: Boolean = true,


        ): Call<GetNews>
}
