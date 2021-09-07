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
    var id: Int = 0,
    var name: String = "Sholat",
    var subtitle: String = "Sholat harian dan tahunan",
    var icon: Int = R.drawable.ic_kabah,
    var alias: String = "sholat"
) : Parcelable

val listAmaliyah = listOf(
    MenuEntity(
        0, "Adab-adab", "Adab Syekh Mursyid",
        R.drawable.ic_adab, "adab-syekh-mursyid"
    ),
    MenuEntity(id = 1),
    MenuEntity(2, "Dzikir", "Dzikir harian", R.drawable.ic_dzikir, "dzikir"),
    MenuEntity(
        3, "Khotaman", "Khotaman TQN PPS Suryalaya",
        R.drawable.ic_khotaman, "khotaman"
    ),
    MenuEntity(
        4, "Manaqib", "Manaqib Tuan Syaikh Abdul Qadir Jailani QS",
        R.drawable.ic_manaqib, "manaqib"
    ),
    MenuEntity(5, "Sholawat", "Kumpulan sholawat", R.drawable.ic_sholawat, "sholawat"),
    MenuEntity(6, "Doa-doa", "Kumpulan doa-doa mustajab", R.drawable.ic_doa, "doa"),
    MenuEntity(7, "Lainnya", icon = R.drawable.ic_lainnya, alias = "more")
)
