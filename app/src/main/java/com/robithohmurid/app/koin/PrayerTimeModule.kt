package com.robithohmurid.app.koin

import com.robithohmurid.app.external.service.PrayHelper
import com.robithohmurid.app.external.service.PrayTime
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi on 03/06/21 20.49
 * iqbal.fauzi.if99@gmail.com
 */
val prayerTimeModule = module {
    single { PrayHelper() }
    single { PrayTime()}
}