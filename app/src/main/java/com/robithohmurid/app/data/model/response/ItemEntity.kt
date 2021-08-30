package com.robithohmurid.app.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ItemEntity(
    @SerializedName("alias") var alias: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String
) : Parcelable