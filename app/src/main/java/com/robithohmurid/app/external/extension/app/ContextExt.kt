package com.robithohmurid.app.external.extension.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.*
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.robithohmurid.app.R
import com.robithohmurid.app.data.local.sholat.LocationData
import com.robithohmurid.app.external.firebase.Crashlytics
import java.io.IOException
import java.util.*

/**
 * Created by Iqbal Fauzi on 11/06/21 22.55
 * iqbal.fauzi.if99@gmail.com
 */
val crashLytic = Crashlytics()
val localeId: Locale
    get() = Locale("id", "ID")

val localeEn: Locale
    get() = Locale("en", "US")

fun Context.getLocationInfo(
    latitude: Double,
    longitude: Double
): MutableList<Address> {
    val geocoder = Geocoder(this, localeId)
    return geocoder.getFromLocation(latitude, longitude, 1)
}

val defaultLocation = LocationData()

fun Context.getCityName(latitude: Double = defaultLocation.latitude, longitude: Double=defaultLocation.longitude): String {
    return try {
        val geocoder = Geocoder(this, localeId)
        geocoder.getFromLocation(latitude, longitude, 1)[0].subAdminArea
    }catch (e : Exception){
        e.printStackTrace()
        crashLytic.recordError(e)
        "Kab. Ciamis"
    }
}

fun Context.getLocalityName(latitude: Double = defaultLocation.latitude, longitude: Double=defaultLocation.longitude): String {
    return try {
        val geocoder = Geocoder(this, localeId)
        geocoder.getFromLocation(latitude, longitude, 1)[0].locality
    }catch (e : Exception){
        e.printStackTrace()
        crashLytic.recordError(e)
        "Kecamatan Panjalu"
    }
}

fun Context.appInstalled(uri: String): Boolean {
    val pm: PackageManager = packageManager
    try {
        pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
    }
    return false
}

fun Activity.hideSoftInput() {
    var view = currentFocus
    if (view == null) view = View(this)
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showSoftInput(edit: EditText) {
    edit.isFocusable = true
    edit.isFocusableInTouchMode = true
    edit.requestFocus()
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(edit, 0)
}

fun Context.toggleSoftInput() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)
inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        val intent = intent<String> {
            Intent.ACTION_SENDTO
            data = Uri.parse(url)
            if (newTask) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        true
    } catch (e: Exception) {
        false
    }
}

fun Context.copyText(label: String, text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)
}

@SuppressLint("QueryPermissionsNeeded")
fun Context.openWebPage(url: String): Boolean {
    // Format the URI properly.
    val uri = url.toWebUri()
    // Try using Chrome Custom Tabs.
    try {
        val intent = CustomTabsIntent.Builder()
            .setToolbarColor(getColorCompat(R.color.colorPrimary))
            .setShowTitle(true)
            .build()
        intent.launchUrl(this, uri)
        return true
    } catch (ignored: Exception) {
        logError(ignored.message.toString())
    }

    // Fall back to launching a default web browser intent.
    try {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            return true
        }
    } catch (ignored: Exception) {
        logError(ignored.message.toString())
    }

    // We were unable to show the web page.
    return false
}

fun Context.isLowRamDevice(): Boolean =
    (this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).isLowRamDevice

fun Context.getBoolean(@BoolRes id: Int) = resources.getBoolean(id)
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)
fun Context.getDrawableCompat(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
fun Context.getInteger(@IntegerRes id: Int) = resources.getInteger(id)
fun Context.isDarkTheme(): Boolean =
    resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun AppCompatImageView.setTint(@ColorInt color: Int) {
    setColorFilter(
        color,
        PorterDuff.Mode.SRC_IN
    )
}

@CheckResult
fun Drawable.tint(@ColorInt color: Int): Drawable {
    val tintedDrawable = DrawableCompat.wrap(this).mutate()
    DrawableCompat.setTint(tintedDrawable, color)
    return tintedDrawable
}

@CheckResult
fun Drawable.tint(context: Context, @ColorRes color: Int): Drawable {
    return tint(context.getColorCompat(color))
}

fun Context.makeCall(number: String): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        false
    }
}

fun Context.makeDial(number: String) {
    try {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$number")
        startActivity(callIntent)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
//        toast(e.localizedMessage)
    } catch (e: SecurityException) {
        e.printStackTrace()
//        toast(e.localizedMessage)
    }
}

fun Context.rate(): Boolean =
    browse("market://details?id=$packageName") or browse("http://play.google.com/store/apps/details?id=$packageName")

fun Context.share(text: String, subject: String = ""): Boolean {
    val intent = Intent().apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    return try {
        startActivity(Intent.createChooser(intent, null))
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

fun Context?.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    this?.let { Toast.makeText(it, text, duration).show() }

fun Context?.showToast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, textId, duration).show() }