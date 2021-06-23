package com.example.newsapp.data.Models.RapidApiNews

import com.example.newsapp.data.Models.RapidApiNews.Image
import com.example.newsapp.data.Models.RapidApiNews.Provider
import com.google.gson.annotations.SerializedName



data class Value (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("url") val url : String,
	@SerializedName("description") val description : String,
	@SerializedName("body") val body : String,
	@SerializedName("snippet") val snippet : String,
	@SerializedName("keywords") val keywordswords : String,
	@SerializedName("language") val language : String,
	@SerializedName("isSafe") val isSafe : Boolean,
	@SerializedName("datePublished") val datePublished : String,
	@SerializedName("provider") val provider : Provider,
	@SerializedName("image") val image : Image
)