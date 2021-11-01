package com.robithohmurid.app.external.firebase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import com.robithohmurid.app.R

/**
 * Created by Iqbal Fauzi on 10/14/21.
 * iqbal.fauzi.if99@gmail.com
 */
class RemoteConfig : OnCompleteListener<Boolean> {

    companion object {
        const val UPDATE_KEY = "update_config"
    }

    val gson = Gson()

    val remoteConfig: FirebaseRemoteConfig by lazy {
        return@lazy FirebaseRemoteConfig.getInstance()
    }

    init {
        remoteConfig.apply {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 600
            }
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate().addOnCompleteListener(this@RemoteConfig)
        }
    }


    override fun onComplete(task: Task<Boolean>) {
//        saveAboutConfig()
    }

//    private fun saveAboutConfig() {
//        val aboutConfigData = remoteConfig.getString(ABOUT_CONFIG)
//        val aboutConfig: AboutConfig? = gson.fromJson(aboutConfigData, AboutConfig::class.java)
//        configSession.setAboutConfig(aboutConfig ?: AboutConfig())
//    }

}