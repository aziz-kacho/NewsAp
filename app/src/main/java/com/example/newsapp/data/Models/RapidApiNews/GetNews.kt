package com.example.newsapp.data.Models.RapidApiNews

import com.google.gson.annotations.SerializedName

data class GetNews(
    @SerializedName("_type") val _type: String,
    @SerializedName("didUMean") val didUMean: String,
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("relatedSearch") val relatedSearch: List<String>,
    @SerializedName("value") val value: List<Value>,
)