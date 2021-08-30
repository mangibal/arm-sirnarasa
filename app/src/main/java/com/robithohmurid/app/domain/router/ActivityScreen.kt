package com.robithohmurid.app.domain.router

/**
 * Created by Iqbal Fauzi on 07/06/21 22.08
 * iqbal.fauzi.if99@gmail.com
 */
sealed class ActivityScreen {
    object SettingSholat: ActivityScreen()
    object JadwalSholat: ActivityScreen()
    object Settings: ActivityScreen()
    object OnBoarding: ActivityScreen()
    object Home: ActivityScreen()
    object ListContent: ActivityScreen()
    object Content: ActivityScreen()
}
