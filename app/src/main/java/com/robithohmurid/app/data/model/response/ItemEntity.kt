package com.robithohmurid.app.data.model.response


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ItemEntity(
    @SerializedName("body") var body: String = ""
) : Parcelable