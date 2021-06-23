package com.example.newsapp.data.Models.RapidApiNews

import com.google.gson.annotations.SerializedName


data class Provider (

	@SerializedName("name") val name : String,
	@SerializedName("favIcon") val favIcon : String,
	@SerializedName("favIconBase64Encoding") val favIconBase64Encoding : String
)