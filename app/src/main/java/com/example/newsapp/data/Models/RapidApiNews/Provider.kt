package com.example.newsapp.data.Models.RapidApiNews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Provider (

	@SerializedName("name") val name : String,
	@SerializedName("favIcon") val favIcon : String,
	@SerializedName("favIconBase64Encoding") val favIconBase64Encoding : String
) : Parcelable