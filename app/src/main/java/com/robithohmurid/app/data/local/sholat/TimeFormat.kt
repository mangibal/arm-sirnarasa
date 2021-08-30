package com.robithohmurid.app.data.local.sholat

import com.robithohmurid.app.external.service.PrayTime

/**
 * Created by Iqbal Fauzi on 08/08/21 19.39
 * iqbal.fauzi.if99@gmail.com
 */
object TimeFormat {

    private val pray = PrayTime()

    val TIME_24 = pray.Time24
    val TIME_12 = pray.Time12

}