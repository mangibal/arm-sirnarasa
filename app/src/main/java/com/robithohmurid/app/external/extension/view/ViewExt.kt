package com.robithohmurid.app.external.extension.view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.robithohmurid.app.R
import com.robithohmurid.app.external.extension.app.getColorCompat

/**
 * Created by Iqbal Fauzi on 11/06/21 23.18
 * iqbal.fauzi.if99@gmail.com
 */

fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

fun View.expand() {
    measure(
        ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )

    val targetHeight: Int = if (0 <= measuredHeight) {
        200
    } else {
        measuredHeight
    }

    layoutParams.height = 0
    show()
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            layoutParams.height = if (interpolatedTime == 1f)
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    a.duration = (targetHeight / context.resources.displayMetrics.density).toInt().toLong()
    startAnimation(a)
}

fun View.collapse() {
    val initialHeight = measuredHeight

    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                hide()
            } else {
                layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // 1dp/ms
    a.duration = (initialHeight / context.resources.displayMetrics.density).toInt().toLong()
    startAnimation(a)
}

fun View.toggleUpDownWithAnimation(): Boolean {
    return if (rotation == 0f) {
        animate().setDuration(150).rotation(180f)
        true
    } else {
        animate().setDuration(150).rotation(0f)
        false
    }
}

fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

fun AppCompatActivity.initToolbar(
    toolbar: Toolbar,
    title: String = "",
    colorTitle: Int = R.color.black
) {
    setSupportActionBar(toolbar)
    supportActionBar?.title = title
    toolbar.setTitleTextColor(getColorCompat(colorTitle))
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.setNavigationOnClickListener { onBackPressed() }
}

/**
 * User Event Listener
 */
fun <T : View> T.onDataClick(block: (T) -> Unit) = setOnClickListener { block(it as T) }
fun <T : View> T.onDataLongClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }
fun <T : View> T.onClick(block: () -> Unit) = setOnClickListener { block() }
fun <T : View> T.onLongClick(block: () -> Boolean) = setOnLongClickListener { block() }

fun View.getString(stringResId: Int): String = resources.getString(stringResId)

fun View.hide() {
    visibility = View.GONE
}

fun View.hideIf(isShow: Boolean = true) {
    visibility = if (isShow) View.GONE else View.VISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.showIf(isShow: Boolean = true) {
    visibility = if (isShow) View.VISIBLE else View.GONE
}

/**
 * Keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}