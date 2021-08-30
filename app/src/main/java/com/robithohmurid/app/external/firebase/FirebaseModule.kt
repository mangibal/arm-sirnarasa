package com.robithohmurid.app.external.firebase

import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi on 06/07/21 19.56
 * iqbal.fauzi.if99@gmail.com
 */
val firebaseModule = module {
    single { CloudMessaging() }
}