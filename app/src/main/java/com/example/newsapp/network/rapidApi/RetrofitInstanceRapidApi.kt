package com.example.newsapp.api

import com.google.android.gms.common.api.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceRapidApi {
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
    fun api() : NewsApi {
        return retrofitInstance().create(NewsApi::class.java)
    }
}