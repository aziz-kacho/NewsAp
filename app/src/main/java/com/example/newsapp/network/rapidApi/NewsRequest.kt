package com.example.newsapp.api

data class NewsRequest (
    val pageNumber : Int,
    val pageSize : Int,
    val withThumbnails : Boolean,
    val location : String
        )