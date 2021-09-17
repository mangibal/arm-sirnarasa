package com.robithohmurid.app.data.model.entity

import android.os.Parcelable
import com.robithohmurid.app.R
import kotlinx.parcelize.Parcelize

/**
 * Created by Iqbal Fauzi on 07/09/21 14.30
 * iqbal.fauzi.if99@gmail.com
 */
@Parcelize
data class MenuEntity(
    var name: String = "Sholat",
    var subtitle: String = "Sholat harian dan tahunan",
    var icon: Int = R.drawable.ic_kabah,
    var alias: String = "sholat"
) : Parcelable
