package com.example.newsapp.data.Models.HistoryNewsEntity

import com.google.gson.annotations.SerializedName


data class NewsResponseHistory (

	@SerializedName("status") val status : String,
	@SerializedName("totalResults") val totalResults : Int,
	@SerializedName("articles") val articles : List<ArticlesHistoryNews>
)