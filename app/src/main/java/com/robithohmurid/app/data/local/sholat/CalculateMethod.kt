package com.robithohmurid.app.data.local.sholat

import com.robithohmurid.app.external.service.PrayTime

/**
 * Created by Iqbal Fauzi on 08/08/21 19.49
 * iqbal.fauzi.if99@gmail.com
 */
object CalculateMethod {

    private val prayTime = PrayTime()
    
    val JAFARI = prayTime.Jafari
    val KARACHI = prayTime.Karachi
    val ISNA = prayTime.ISNA
    val MWL = prayTime.MWL
    val MAKKAH = prayTime.Makkah
    val EGYPT = prayTime.Egypt
    val CUSTOM = prayTime.Custom
    val TEHRAN = prayTime.Tehran
    val KEMENAG = prayTime.KEMENAG

}