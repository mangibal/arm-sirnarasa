package com.robithohmurid.app.external.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.robithohmurid.app.external.extension.app.logInfo

/**
 * Created by Iqbal Fauzi on 06/07/21 19.42
 * iqbal.fauzi.if99@gmail.com
 */
class Crashlytics {

    private val crashlytics: FirebaseCrashlytics by lazy {
        return@lazy FirebaseCrashlytics.getInstance()
    }

    //    fun setUserProfile(accountEntity: AccountEntity) {
//        crashlytics.apply {
//            setUserId(accountEntity.id.toString())
//            setKey("name", accountEntity.name.toString())
//            setKey("email", accountEntity.email.toString())
//            setKey("phone", accountEntity.telephone.toString())
//            setKey("uuid", UUID.randomUUID().toString())
//        }
//    }

    fun setLog(message: String) {
        crashlytics.log(message)
    }

    fun recordError(e: Throwable) {
        logInfo("LoginfoError")
        crashlytics.recordException(e)
    }

    fun setKey(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Int) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Double) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Float) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Boolean) {
        crashlytics.setCustomKey(key, value)
    }

    fun setKey(key: String, value: Long) {
        crashlytics.setCustomKey(key, value)
    }

}