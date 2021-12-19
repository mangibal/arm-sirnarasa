package com.robithohmurid.app.domain.router

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by Iqbal Fauzi on 07/06/21 22.05
 * iqbal.fauzi.if99@gmail.com
 */
interface ScreenRouter {
    fun navigateToContent(context: Activity, title: String, bodyContent: String)
    fun navigateToListContent(context: Activity, category: String, alias: String, title: String)
    fun gotoSettingSholat(context: Activity)
    fun navigateToJadwalSholat(context: Activity)
    fun navigateToSettings(context: Activity)
    fun navigateToOnBoardingPage(context: Activity)
    fun navigateToHomePage(context: Activity)
    fun getIntentScreen(context: Context, screen: ActivityScreen): Intent
}