package com.robithohmurid.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingContentEntity(
    val id: Int,
    val title: String,
    val listContent: List<ContentEntity> = listOf()
) : Parcelable