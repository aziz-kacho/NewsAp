package com.example.newsapp.data.Models.RapidApiNews

import com.google.gson.annotations.SerializedName




data class Image (

	@SerializedName("url") val url : String,
	@SerializedName("height") val height : Int,
	@SerializedName("width") val width : Int,
	@SerializedName("thumbnail") val thumbnail : String,
	@SerializedName("thumbnailHeight") val thumbnailHeight : Int,
	@SerializedName("thumbnailWidth") val thumbnailWidth : Int,
	@SerializedName("base64Encoding") val base64Encoding : String,
	@SerializedName("name") val name : String,
	@SerializedName("title") val title : String,
	@SerializedName("provider") val provider : Provider,
	@SerializedName("imageWebSearchUrl") val imageWebSearchUrl : String,
	@SerializedName("webpageUrl") val webpageUrl : String
)