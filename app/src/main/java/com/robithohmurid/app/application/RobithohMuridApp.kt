package com.robithohmurid.app.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.robithohmurid.app.koin.appModules
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.robithohmurid.app.external.extension.app.timberInitialization
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Iqbal Fauzi on 03/06/21 19.02
 * iqbal.fauzi.if99@gmail.com
 */
class RobithohMuridApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initLogger()
        initHawk()
        initKoin()
    }

    private fun initLogger() {
        timberInitialization()
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initHawk() {
        Hawk.init(this).build()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@RobithohMuridApp)
            modules(appModules)
        }
    }

}