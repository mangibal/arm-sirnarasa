package com.robithohmurid.app.external.firebase

import com.google.firebase.messaging.FirebaseMessaging
import com.robithohmurid.app.external.extension.app.logInfo

/**
 * Created by Iqbal Fauzi on 06/07/21 19.49
 * iqbal.fauzi.if99@gmail.com
 */
class CloudMessaging {

    private val messaging: FirebaseMessaging by lazy {
        return@lazy FirebaseMessaging.getInstance()
    }

    init {
        messaging.token.addOnCompleteListener {
            if (!it.isSuccessful) {
                logInfo("Fetching registration Token failed")
                return@addOnCompleteListener
            }
            // Get new FCM registration token
            val token = it.result
            logInfo("Token $token")
        }
    }

    val token = messaging.token
}