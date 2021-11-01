package com.robithohmurid.app.data.model.entity


import com.google.gson.annotations.SerializedName

data class UpdateEntity(
    @SerializedName("isForceUpdate") val isForceUpdate: Boolean = false,
    @SerializedName("isUpdate") val isUpdate: Boolean = false,
    @SerializedName("versionCode") val versionCode: Int = 1,
    @SerializedName("versionName") val versionName: String = "1.0.0"
)