package com.example.newsapp.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilderForToken = original.newBuilder()
                .method(original.method(), original.body())
            val requestToken = requestBuilderForToken.build()
            chain.proceed(requestToken)
        }.build()

    private fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun api(): Api {
        return retrofitInstance().create(Api::class.java)
    }

}