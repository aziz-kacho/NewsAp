package com.example.newsapp.data.Models.HistoryNewsEntity

import com.google.gson.annotations.SerializedName


data class SourceHistory (
	@SerializedName("id") val ids : String?,
	@SerializedName("name") val name : String
)