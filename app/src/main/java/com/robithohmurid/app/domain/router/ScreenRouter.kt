package com.robithohmurid.app.domain.router

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by Iqbal Fauzi on 07/06/21 22.05
 * iqbal.fauzi.if99@gmail.com
 */
interface ScreenRouter {
    fun gotoSettingSholat(context: Activity)
    fun gotoJadwalSholat(context: Activity)
    fun gotoSettings(context: Activity)
    fun gotoOnBoardingPage(context: Activity)
    fun gotoHomePage(context: Activity)
    fun getIntentScreen(context: Context, screen: ActivityScreen): Intent
}