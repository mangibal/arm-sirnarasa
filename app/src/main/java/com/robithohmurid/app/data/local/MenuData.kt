package com.robithohmurid.app.data.local

import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.entity.MenuEntity
import com.robithohmurid.app.external.constant.MenuConstant

/**
 * Created by Iqbal Fauzi on 12/09/21 10.15
 * iqbal.fauzi.if99@gmail.com
 */
val listAmaliyahGrid = listOf(
    MenuEntity(
        "Adab-adab", "Adab sehari-hari Syekh Mursyid",
        R.drawable.ic_adab, MenuConstant.ADAB
    ),
    MenuEntity(),
    MenuEntity("Dzikir", "Dzikir harian", R.drawable.ic_dzikir, MenuConstant.DZIKIR),
    MenuEntity(
        "Khotaman", "Khotaman TQN PPS Suryalaya",
        R.drawable.ic_khotaman, MenuConstant.KHOTAMAN
    ),
    MenuEntity(
        "Manaqib", "Manaqib Tuan Syaikh Abdul Qadir Jailani QS",
        R.drawable.ic_manaqib, MenuConstant.MANAQIB
    ),
    MenuEntity("Sholawat", "Kumpulan sholawat", R.drawable.ic_sholawat, MenuConstant.SHOLAWAT),
    MenuEntity("Doa-doa", "Kumpulan doa-doa mustajab", R.drawable.ic_doa, MenuConstant.DOA),
    MenuEntity("Lainnya", icon = R.drawable.ic_lainnya, alias = "more")
)

val listAmaliyah = listOf(
    MenuEntity(
        "Adab-adab", "Adab sehari-hari Syekh Mursyid",
        R.drawable.ic_adab, MenuConstant.ADAB
    ),
    MenuEntity(),
    MenuEntity("Dzikir", "Dzikir harian", R.drawable.ic_dzikir, MenuConstant.DZIKIR),
    MenuEntity(
        "Khotaman", "Khotaman TQN PPS Suryalaya",
        R.drawable.ic_khotaman, MenuConstant.KHOTAMAN
    ),
    MenuEntity(
        "Manaqib", "Manaqib Tuan Syaikh Abdul Qadir Jailani QS",
        R.drawable.ic_manaqib, MenuConstant.MANAQIB
    ),
    MenuEntity(
        "Tawashul", "Tawashul TQN Ma'had Suryalaya Sirnarasa",
        icon = R.drawable.ic_tawashul, alias = MenuConstant.TAWASSUL
    ),
    MenuEntity(
        "Tanbih",
        "Tanbih TQN Ma'had Suryalaya Sirnarasa",
        icon = R.drawable.ic_tanbih,
        alias = MenuConstant.TANBIH
    ),
    MenuEntity("Sholawat", "Kumpulan sholawat", R.drawable.ic_sholawat, MenuConstant.SHOLAWAT),
    MenuEntity("Doa-doa", "Kumpulan doa-doa mustajab", R.drawable.ic_doa, MenuConstant.DOA),
)

val listMenuTqn = listOf(
    MenuEntity(
        "Tujuan dan Dasar TQN", "Tujuan dan Dasar TQN Suryalaya",
        R.drawable.ic_tqn, alias = MenuConstant.TUJUAN_DASAR
    ),
    MenuEntity(
        "Diagram Latifah", "Latifah yang ada alam qolbu manusia",
        R.drawable.ic_latifah, alias = MenuConstant.DIAGRAM_LATIFAH
    ),
    MenuEntity(
        "Tahlil",
        "Amaliyah Tahlil/Ziarah Kubur",
        R.drawable.ic_ziarah, MenuConstant.ZIARAH
    ),
    MenuEntity(
        "Silsilah TQN", "Silsilah TQN Ma'had Suryalaya Sirnarasa",
        R.drawable.ic_silsilah, alias = MenuConstant.SILSILAH
    ),
    MenuEntity(
        "Syekh Abdul Qodir Al-Jailani",
        "Nama-nama Syekh Abdul Qodir",
        R.drawable.ic_manaqib, alias = MenuConstant.SYEKH
    ),
    MenuEntity(
        "Tarhim",
        "Tarhim TQN Ma'had Suryalaya Sirnarasa",
        R.drawable.ic_tarhim, alias = MenuConstant.TARHIM
    )
)

val listSholat = listOf(
    MenuEntity("Sholat Harian", icon = R.drawable.ic_kabah, alias = MenuConstant.SHOLAT_HARIAN),
    MenuEntity(
        "Sholat Tahunan",
        icon = R.drawable.ic_kabah,
        alias = MenuConstant.SHOLAT_TAHUNAN
    )
)

val listMenuManaqib = listOf(
    MenuEntity("MC", icon = R.drawable.ic_mic, alias = MenuConstant.MC_MANAQIB),
    MenuEntity(
        "Sholawat",
        icon = R.drawable.ic_sholawat,
        alias = MenuConstant.SHOLAWAT_THORIQIYYAH
    ),
    MenuEntity("Tanbih", icon = R.drawable.ic_tanbih, alias = MenuConstant.TANBIH),
    MenuEntity("Tawashul", icon = R.drawable.ic_tawashul, alias = MenuConstant.TAWASSUL),
    MenuEntity("Manqobah", icon = R.drawable.ic_manqobah, alias = MenuConstant.MANQOBAH)
)