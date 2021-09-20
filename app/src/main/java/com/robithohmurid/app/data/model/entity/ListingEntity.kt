package com.robithohmurid.app.data.model.entity

import android.os.Parcelable
import com.robithohmurid.app.data.model.entity.ContentEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingEntity(
    val id: Int,
    val title: String,
    val content: String
) : Parcelable