package com.robithohmurid.app.external.extension.app

import android.content.Context
import android.net.Uri
import android.text.Html
import android.text.Spanned
import androidx.core.net.toUri
import com.robithohmurid.app.R
import com.robithohmurid.app.external.constant.ContentConstant
import com.robithohmurid.app.external.constant.MenuConstant
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Iqbal Fauzi on 11/06/21 23.09
 * iqbal.fauzi.if99@gmail.com
 */
fun Int.getAliasById(): String {
    return when (this) {
        MenuConstant.ID_ADAB -> ContentConstant.ADAB_KEY
        else -> ContentConstant.ADAB_KEY
    }
}

fun String.isJSONValid(): Boolean {

    if (this.isEmpty()) {
        return false
    }

    try {
        JSONObject(this)
    } catch (ex: JSONException) {
        // edited, to include @Arthur's comment
        // e.g. in case JSONArray is valid as well...
        try {
            JSONArray(this)
        } catch (ex1: JSONException) {
            return false
        }

    }

    return true
}

fun String.toWebUri(): Uri {
    return (if (startsWith("http://") || startsWith("https://")) this else "https://$this").toUri()
}

fun String.asHtml(): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String.toTitleCase(): String {
    var space = true
    val builder = StringBuilder(this)
    val len = builder.length

    for (i in 0 until len) {
        val c = builder[i]
        if (space) {
            if (!Character.isWhitespace(c)) {
                builder.setCharAt(i, Character.toTitleCase(c))
                space = false
            }
        } else if (Character.isWhitespace(c)) {
            space = true
        } else {
            builder.setCharAt(i, Character.toLowerCase(c))
        }
    }
    return builder.toString()
}

fun Context.toHoursFormat(minute: Int): String {
    if (minute < 60) {
        return String.format("$minute %s", getString(R.string.mins))
    }
    val time = minute.toDouble() / 60
    if (minute % 60 == 0) {
        return String.format("${time.toInt()} %s", getString(R.string.hours))
    }
    return String.format("$time %s", getString(R.string.hours))
}

fun Context.toHoursFormat(minute: Double): String {
    if (minute == 0.5) {
        return String.format("30 %s", getString(R.string.mins))
    }
    if (minute % 1.0 == 0.0) {
        return String.format("${minute.toInt()} %s", getString(R.string.hours))
    }
    return String.format("$minute %s", getString(R.string.hours))
}