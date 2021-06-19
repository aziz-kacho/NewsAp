package tj.livo.newsapp.models

import com.google.gson.annotations.SerializedName


data class SourceSave (
	@SerializedName("id") val ids : String?,
	@SerializedName("name") val name : String
)