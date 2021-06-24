package com.example.newsapp.network.searchRapidApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceSearchRapidApi {
    private val client = OkHttpClient.Builder()
        .addInterceptor{ chain ->
            val original = chain.request()
            val requestBuilderForToken = original.newBuilder()
                .method(original.method(),original.body())
            val requestToken = requestBuilderForToken.build()
            chain.proceed(requestToken)
        }.build()

    private fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://contextualwebsearch-websearch-v1.p.rapidapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun api() : SearchRapidApi {
        return retrofitInstance().create(SearchRapidApi::class.java)
    }
}