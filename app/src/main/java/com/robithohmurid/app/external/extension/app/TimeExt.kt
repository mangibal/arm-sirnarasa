package com.robithohmurid.app.external.extension.app

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Iqbal Fauzi on 3/17/21 9:59 AM
 * iqbal.fauzi.if99@gmail.com
 */
val currentHour: Int
    get() {
        return getCalendarInstance().get(Calendar.HOUR_OF_DAY)
    }

val currentMinute: Int
    get() {
        return getCalendarInstance().get(Calendar.MINUTE)
    }

val currentSecond: Int
    get() {
        return getCalendarInstance().get(Calendar.SECOND)
    }

val currentTime: String
    get() = "$currentHour:$currentMinute:$currentSecond"

fun Long.convertLongToTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}

fun String.addMinutes(minute: Int, timeFormat: String = "HH.mm"): String {
    val timeFormatter = SimpleDateFormat(timeFormat, Locale.getDefault())
    val d: Date = timeFormatter.parse(this)
    val cal = Calendar.getInstance()
    cal.time = d
    cal.add(Calendar.MINUTE, minute)
    return timeFormatter.format(cal.time)
}

fun getNear15Minute(minutes: Int): Int {
    val mod = minutes % 15
    var res = 0
    res = minutes + (15 - mod)
    return res //return rounded minutes
}

fun String.isLessThan(endtime: String): Boolean {
    val pattern = "HH:mm"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    try {
        val date1 = sdf.parse(this)
        val date2 = sdf.parse(endtime)
        return date1.before(date2)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return false
}

fun String.isMoreThan(endtime: String): Boolean {
    val pattern = "HH:mm"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    try {
        val date1 = sdf.parse(this)
        val date2 = sdf.parse(endtime)
        return date1.after(date2)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return false
}
