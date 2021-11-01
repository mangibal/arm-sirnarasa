package com.robithohmurid.app.external.service

import android.os.Build
import com.robithohmurid.app.data.local.sholat.LocationData
import com.robithohmurid.app.data.local.SessionHelper
import com.robithohmurid.app.data.model.entity.SholatEntity
import com.robithohmurid.app.external.constant.DateTimeFormat
import com.robithohmurid.app.external.constant.SessionConstant
import com.robithohmurid.app.external.extension.app.formatToString
import com.robithohmurid.app.external.extension.app.getCalendarInstance

/**
 * Created by Iqbal Fauzi on 10/07/21 16.43
 * iqbal.fauzi.if99@gmail.com
 */
class PrayHelper {

    private val sessionHelper = SessionHelper()

    fun setLocation(locationData: LocationData) = sessionHelper.setLocation(locationData)

    fun getLocation(): LocationData = sessionHelper.getLocation()

    fun setLocationType(type: Int) = sessionHelper.addSession(SessionConstant.LOCATION_TYPE, type)

    fun getLocationType(): Int = sessionHelper.getSession(SessionConstant.LOCATION_TYPE)

    fun setTimeFormat() {

    }

    fun getPrayTimeList(year: Int = 0, month: Int = 0, date: Int = 0): List<SholatEntity> {
        val prayTime = PrayTime()
        prayTime.run {
            timeFormat = Time24
            calcMethod = Custom
            asrJuristic = Shafii
            adjustHighLats = AngleBased
            setFajrAngle(20.0)
            setIshaAngle(18.0)
        }

        val offsets =
            intArrayOf(2, 3, 3, 2, 4, 0, 2) // { Subuh,Isyroq,Dhuhr,Asr,Sunset,Maghrib,Isha }

        prayTime.tune(offsets)

        val cal = getCalendarInstance()

        if (year != 0) {
            cal.set(year, month, date)
        }

        val time = cal.time.toLocaleString()
        val dateFormatted = time.formatToString(
            DateTimeFormat.CALENDAR_ID_FORMAT,
            DateTimeFormat.DEFAULT_DATE_FORMAT
        )

        val location = getLocation()
        val prayerTimes = prayTime.getPrayerTimes(
            cal,
            location.latitude, location.longitude, 7.0
        )

        val prayerNames = prayTime.timeNames

        val listSholat = mutableListOf<SholatEntity>()
        val listSholatFinal = mutableListOf<SholatEntity>()
        listSholat.clear()
        for (i in prayerNames.indices) {
            listSholat.add(
                SholatEntity(
                    id = i,
                    name = prayerNames[i],
                    time = prayerTimes[i],
                    date = dateFormatted,
                    dateTime = String.format("%s %s", dateFormatted, prayerTimes[i])
                )
            )
        }

        listSholatFinal.clear()
        for (i in listSholat.indices) {
            val name = listSholat[i].name
            if (name != "Isyroq" && name != "Maghrib") {
                listSholatFinal.add(listSholat[i])
            }
        }

        return listSholatFinal
    }

}