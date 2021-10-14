package com.robithohmurid.app.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.robithohmurid.app.BuildConfig
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.entity.UpdateEntity
import com.robithohmurid.app.databinding.ActivitySplashBinding
import com.robithohmurid.app.domain.abstraction.BaseActivity
import com.robithohmurid.app.external.firebase.RemoteConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(
    ActivitySplashBinding::inflate,
    SplashViewModel::class
), UpdateListener {

    override fun onInitUI(savedInstanceState: Bundle?) {
        hideSystemUI()
        with(binding) {
            tvCredit.text = String.format(
                "%s %s",
                getString(R.string.title_created_for),
                getString(R.string.title_stid_sirnarasa)
            )

            checkConfig()

//            lifecycleScope.launch(Dispatchers.IO) {
//                delay(2000)
//                withContext(Dispatchers.Main) {
//                    if (viewModel.sessionHelper.isOnboarding()) {
//                        router.gotoOnBoardingPage(this@SplashActivity)
//                    } else {
//                        router.gotoHomePage(this@SplashActivity)
//                    }
//                }
//            }
        }
    }

    private fun checkConfig() {
        if (viewModel.sessionHelper.isOnboarding()) {
            router.gotoOnBoardingPage(this@SplashActivity)
        } else {
            remote.remoteConfig.fetch().addOnCompleteListener {
                if (it.isSuccessful) {
                    val updateConfig = remote.remoteConfig.getString(RemoteConfig.UPDATE_KEY)
                    val updateEntity: UpdateEntity =
                        gson.fromJson(updateConfig, UpdateEntity::class.java) ?: UpdateEntity()
                    checkUpdate(updateEntity)
                } else {
                    router.gotoHomePage(this@SplashActivity)
                }
            }
        }
    }

    private fun checkUpdate(updateEntity: UpdateEntity) {
        if (updateEntity.versionCode > BuildConfig.VERSION_CODE) {
            UpdateDialog().run {
                show(supportFragmentManager, this.tag)
            }
        } else {
            router.gotoHomePage(this@SplashActivity)
        }
    }

    @Suppress("DEPRECATION")
    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onInitData() {

    }

    override fun onUpdateListener() {
        val url = "https://play.google.com/store/apps/details?id=com.robithohmurid.app"
        startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
    }

    override fun onLaterListener() {
        router.gotoHomePage(this@SplashActivity)
    }

}