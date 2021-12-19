package com.robithohmurid.app.domain.router

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.constant.IntentKey
import com.robithohmurid.app.external.extension.app.logError
import com.robithohmurid.app.presentation.content.ContentActivity
import com.robithohmurid.app.presentation.listcontent.ListContentActivity
import com.robithohmurid.app.presentation.main.MainActivity
import com.robithohmurid.app.presentation.onboarding.OnBoardingActivity
import com.robithohmurid.app.presentation.settings.SettingsActivity
import com.robithohmurid.app.presentation.sholat.jadwal.JadwalSholatActivity
import com.robithohmurid.app.presentation.sholat.setting.SholatSettingActivity

/**
 * Created by Iqbal Fauzi on 07/06/21 22.06
 * iqbal.fauzi.if99@gmail.com
 */
class ScreenRouterImpl(private val application: Application) : ScreenRouter {

    companion object {
        const val TAG = "ROUTER"
    }

    override fun navigateToContent(context: Activity, title: String, bodyContent: String) {
        val screen = getIntentScreen(context, ActivityScreen.Content)
        val bundle = Bundle().apply {
            putString(IntentKey.TITLE_KEY, title)
            putString(IntentKey.CONTENT_KEY, bodyContent)
        }
        openActivity(context, screen, bundle)
    }

    override fun navigateToListContent(
        context: Activity,
        category: String,
        alias: String,
        title: String
    ) {
        val screen = getIntentScreen(context, ActivityScreen.ListContent)
        val bundle = Bundle().apply {
            putString(IntentKey.CATEGORY_KEY, category)
            putString(IntentKey.CONTENT_KEY, alias)
            putString(IntentKey.TITLE_KEY, title)
        }
        openActivity(context, screen, bundle)
    }

    override fun gotoSettingSholat(context: Activity) {
        val screen = getIntentScreen(context, ActivityScreen.SettingSholat)
        openActivity(context, screen)
    }

    override fun navigateToJadwalSholat(context: Activity) {
        val screen = getIntentScreen(context, ActivityScreen.JadwalSholat)
        openActivity(context, screen)
    }

    override fun navigateToSettings(context: Activity) {
        val screen = getIntentScreen(context, ActivityScreen.Settings)
        openActivity(context, screen)
    }

    override fun navigateToOnBoardingPage(context: Activity) {
        val screen = getIntentScreen(context, ActivityScreen.OnBoarding)
        screen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        openActivity(context, screen)
    }

    override fun navigateToHomePage(context: Activity) {
        val screen = getIntentScreen(context, ActivityScreen.Main)
        screen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        openActivity(context, screen)
    }

    override fun getIntentScreen(context: Context, screen: ActivityScreen): Intent {
        val klazz: Class<*> = when (screen) {
            ActivityScreen.SettingSholat -> SholatSettingActivity::class.java
            ActivityScreen.JadwalSholat -> JadwalSholatActivity::class.java
            ActivityScreen.Settings -> SettingsActivity::class.java
            ActivityScreen.OnBoarding -> OnBoardingActivity::class.java
            ActivityScreen.Main -> MainActivity::class.java
            ActivityScreen.ListContent -> ListContentActivity::class.java
            ActivityScreen.Content -> ContentActivity::class.java
        }
        return Intent(context, klazz)
    }

    private fun openActivity(
        context: Activity,
        intent: Intent,
        bundle: Bundle? = null,
        isFinish: Boolean = false
    ) {
        try {
            bundle?.let { intent.putExtras(bundle) }
            intent.run { context.startActivity(this) }
            if (isFinish) context.finish()
        } catch (ex: ClassNotFoundException) {
            logError(ex.message.toString())
            sendBroadcastMessage()
        }
    }

    private fun sendBroadcastMessage(message: String = "Screen not found!") {
        val intent = Intent(BaseActivity.ROUTER_MESSAGE)
        intent.putExtra(BaseActivity.MESSAGE, message)
        LocalBroadcastManager.getInstance(application).sendBroadcast(intent)
    }

}