package com.example.newsapp.data.Models.RapidApiNews

import com.example.newsapp.data.Models.RapidApiNews.Image
import com.example.newsapp.data.Models.RapidApiNews.Provider
import com.google.gson.annotations.SerializedName



data class Value (

	@SerializedName("id") val id : String? = null,
	@SerializedName("title") val title : String? = null,
	@SerializedName("url") val url : String? = null,
	@SerializedName("description") val description : String? = null,
	@SerializedName("body") val body : String? = null,
	@SerializedName("snippet") val snippet : String? = null,
	@SerializedName("keywords") val keywordswords : String? = null,
	@SerializedName("language") val language : String? = null,
	@SerializedName("isSafe") val isSafe : Boolean? = null,
	@SerializedName("datePublished") val datePublished : String? = null,
	@SerializedName("provider") val provider : Provider? = null,
	@SerializedName("image") val image : Image? = null
)