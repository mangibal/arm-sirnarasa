package com.robithohmurid.app.data.model.entity

import android.os.Parcelable
import com.robithohmurid.app.data.model.entity.ContentEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingContentEntity(
    val id: Int,
    val title: String,
    val listContent: List<ContentEntity> = listOf()
) : Parcelable