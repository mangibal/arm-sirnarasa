package com.robithohmurid.app.external.extension.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.robithohmurid.app.R

/**
 * Created by Iqbal Fauzi on 11/06/21 23.16
 * iqbal.fauzi.if99@gmail.com
 */
fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) =
    snack(messageRes, length) {}

inline fun View.snack(
    @StringRes messageRes: Int,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, messageRes, length)
    snack.f()
    snack.show()
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) = snack(message, length) {}
inline fun View.snack(
    message: String,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

@SuppressLint("ResourceAsColor")
fun Snackbar.action(text: String, @ColorRes color: Int? = null, listener: (View) -> Unit) {
    setAction(text, listener)
    color?.let { setActionTextColor(color) }
}

fun Snackbar.configSnackBar(backgroundResource: Int) {
    val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(8, 8, 8, 8)
    this.view.apply {
        layoutParams = params
        background = ContextCompat.getDrawable(context, backgroundResource)
    }
    ViewCompat.setElevation(this.view, 8f)
}

fun View.snackBarWhite(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    val sbView = snackbar.view
    val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
    textView.setTextColor(Color.RED)
    snackbar.apply {
        configSnackBar(R.drawable.bg_snackbar_white)
        show()
    }
}

fun View.snackBarAccent(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    val sbView = snackbar.view
    sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
    val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}

fun View.snackBar(message: String, color: Int = R.color.colorPrimaryDark) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    val sbView = snackbar.view
    sbView.setBackgroundColor(ContextCompat.getColor(context, color))
    val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}

fun View.snackBarRed(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    val sbView = snackbar.view
    val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    snackbar.apply {
        configSnackBar(R.drawable.bg_snackbar_red)
        show()
    }
}