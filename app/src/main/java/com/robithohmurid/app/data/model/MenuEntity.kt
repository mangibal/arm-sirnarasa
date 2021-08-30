package com.robithohmurid.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Iqbal Fauzi on 11/06/21 21.36
 * iqbal.fauzi.if99@gmail.com
 */
@Parcelize
data class MenuEntity(
    @SerializedName("title") val title: String
) : Parcelable
