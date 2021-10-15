package com.robithohmurid.app.external.extension.app

import com.robithohmurid.app.external.constant.DateTimeFormat
import com.robithohmurid.app.external.constant.DateTimeFormat.DEFAULT_DATE_TIME_FORMAT
import com.robithohmurid.app.external.constant.DateTimeFormat.D_MMM_YYYY_FORMAT
import org.joda.time.DateTime
import org.joda.time.chrono.IslamicChronology
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Iqbal Fauzi on 3/17/21 4:05 PM
 * iqbal.fauzi.if99@gmail.com
 */
fun getCalendarInstance(): Calendar = Calendar.getInstance(localeId)

val currentDateTime: String
    get() = "$currentDate $currentTime"

val currentDate: String
    get() = "$currentYear-$currentMonth-$currentDay"

val currentDay: String
    get() {
        val day = getCalendarInstance().get(Calendar.DAY_OF_MONTH)
        return if (day < 10) {
            "0$day"
        } else {
            day.toString()
        }
    }

fun Calendar.getDayByCalendar(): Int {
    return get(Calendar.DAY_OF_MONTH)
}

val currentMonth: String
    get() {
        val month = (getCalendarInstance().get(Calendar.MONTH))
        return if (month < 10) {
            "0$month"
        } else {
            month.toString()
        }
    }

fun Calendar.getMonthByCalendar(): Int {
    return get(Calendar.MONTH)
}

val currentYear: Int
    get() {
        return getCalendarInstance().get(Calendar.YEAR)
    }

fun Calendar.getYearByCalendar(): Int {
    return get(Calendar.YEAR)
}

fun getIslamicDate(): String {
    val dateMonth = (getCalendarInstance().get(Calendar.MONTH) + 1)

    // setup date object for midday on May Day 2004 (ISO year 2004)
    val dtISO = DateTime(
        currentYear, dateMonth, currentDay.toInt(),
        0, 0, 0, 0
    )
    // find out what the same instant is using the Islamic Chronology
    val dtIslamic: DateTime = dtISO.withChronology(IslamicChronology.getInstance())
    val localDate = dtIslamic.toLocalDate()
    return localDate.toString("dd M yyyy").toReadableIslamicDate()
}

fun String.toReadableIslamicDate(): String {
    val splitted = this.split(" ")
    val day = splitted[0]
    val month = splitted[1].toInt()
    val year = splitted[2]

    val monthName = when (month) {
        1 -> "Muharram"
        2 -> "Safar"
        3 -> "Rabi'ul Awal"
        4 -> "Rabi'ul Akhir"
        5 -> "Jumadil Awal"
        6 -> "Jumadil Akhir"
        7 -> "Rajab"
        8 -> "Sya’ban"
        9 -> "Ramadhan"
        10 -> "Syawal"
        11 -> "Dzulqaidah"
        12 -> "Dzulhijjah"
        else -> month
    }

    return String.format("%s %s %s H", day, monthName, year)
}

fun String.defaultToWithDay(): String {
    val defaultDateFormat =
        SimpleDateFormat(DateTimeFormat.DEFAULT_DATE_FORMAT, Locale.getDefault())
    val newDateFormat =
        SimpleDateFormat(DateTimeFormat.DATE_FORMAT_WITH_DAY, Locale.getDefault())
    return newDateFormat.format(defaultDateFormat.parseObject(this))
}

fun getCalculatedDateByToday(
    dateFormat: String = DateTimeFormat.DEFAULT_DATE_FORMAT,
    days: Int = 1
): String {
    val s = SimpleDateFormat(dateFormat, Locale.getDefault())
    getCalendarInstance().add(Calendar.DAY_OF_YEAR, days)
    return s.format(Date(getCalendarInstance().timeInMillis))
}

fun String.getCalculatedDateByDate(
    dateFormat: String = DateTimeFormat.DEFAULT_DATE_FORMAT,
    days: Int = 1
): String? {
    val cal = Calendar.getInstance()
    val s = SimpleDateFormat(dateFormat, Locale.getDefault())
    val parsing = this.split("-")
    val year = parsing[0].toInt()
    val month = parsing[1].toInt() - 1
    val dates = parsing[2].toInt()
    cal.set(year, month, dates)
    cal.add(Calendar.DAY_OF_YEAR, days)
    try {
        return s.format(Date(cal.timeInMillis))
    } catch (e: ParseException) {
        Timber.e("Error in Parsing Date : %s", e.message)
    }
    return null
}

fun String.getCalculatedDateByDateAsMillis(days: Int = 1): Long {
    val cal = Calendar.getInstance()
    val parsing = this.split("-")
    val year = parsing[0].toInt()
    val month = parsing[1].toInt() - 1
    val dates = parsing[2].toInt()
    cal.set(year, month, dates)
    cal.add(Calendar.DAY_OF_YEAR, days)
    try {
        return cal.timeInMillis
    } catch (e: ParseException) {
        Timber.e("Error in Parsing Date : %s", e.message)
    }
    return 0
}

fun getDateRange(
    startDate: String,
    startFormat: String = DateTimeFormat.DEFAULT_DATE_FORMAT,
    endDate: String,
    endFormat: String = DateTimeFormat.DEFAULT_DATE_FORMAT
): String {
    try {
        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()
        val startDF = SimpleDateFormat(startFormat, Locale.getDefault())
        val endDF = SimpleDateFormat(endFormat, Locale.getDefault())
        val startParsedDate = startDF.parse(startDate) as Date
        val endParsedDate = endDF.parse(endDate) as Date

        startCalendar.time = startParsedDate
        endCalendar.time = endParsedDate

        val finalStartFormat: String
        val finalEndFormat: String

        // Masih sama tahun nya
        if (startCalendar.get((Calendar.YEAR)) == endCalendar.get(Calendar.YEAR)) {
            // Bulan yang sama
            if (startCalendar.get((Calendar.MONTH)) == endCalendar.get(Calendar.MONTH)) {
                finalStartFormat = "dd"
                finalEndFormat = "dd MMM yyyy"
            } else {
                finalStartFormat = "dd MMM"
                finalEndFormat = "dd MMM yyyy"
            }
        } else {
            finalStartFormat = "dd MMM yyyy"
            finalEndFormat = "dd MMM yyyy"
        }

        val finalStartDate = SimpleDateFormat(finalStartFormat, Locale.getDefault())
        val finalEndDate = SimpleDateFormat(finalEndFormat, Locale.getDefault())

        val ok = finalStartDate.format(startDF.parseObject(startDate))
        val ok1 = finalEndDate.format(endDF.parseObject(endDate))

        return String.format("%s - %s", ok, ok1)
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}

fun String.dateToLong(pattern: String = DateTimeFormat.DEFAULT_DATE_TIME_FORMAT): Long {
    val dateFormat = SimpleDateFormat(pattern, Locale("id", "ID")).parse(this)
    return dateFormat.time
}

fun Long.compareWith(date: Long): Long {
    return this - date
}

fun String.formatToString(
    startFormat: String = DEFAULT_DATE_TIME_FORMAT,
    endFormat: String = D_MMM_YYYY_FORMAT
): String {
    val startDate = SimpleDateFormat(startFormat, Locale.getDefault())
    val endDate = SimpleDateFormat(endFormat, Locale.getDefault())
    val date = try {
        endDate.format(startDate.parseObject(this))
    } catch (ex: Exception) {
        ex.printStackTrace()
        ""
    }
    return date
}