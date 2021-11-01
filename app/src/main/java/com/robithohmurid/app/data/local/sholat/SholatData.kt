package com.robithohmurid.app.data.local.sholat

/**
 * Created by Iqbal Fauzi on 19/07/21 09.51
 * iqbal.fauzi.if99@gmail.com
 */
data class SholatEntity(
    var id: Int = 0,
    var name: String = "Shubuh",
    var time: String = "04:24",
    var isNotification: Boolean = false
)

val listSholat = listOf(
    SholatEntity(isNotification = true),
    SholatEntity(1, "Isyroq", "05:38", false),
    SholatEntity(2, "Zuhur", "11:49", false),
    SholatEntity(3, "Asar", "14:57", false),
    SholatEntity(4, "Magrib", "17:47", true),
    SholatEntity(5, "Isya'", "18:56", false),
)
