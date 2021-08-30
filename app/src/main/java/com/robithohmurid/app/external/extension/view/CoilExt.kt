package com.robithohmurid.app.external.extension.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.transform.RoundedCornersTransformation
import com.robithohmurid.app.R
import com.robithohmurid.app.external.extension.app.getColorCompat

/**
 * Created by Iqbal Fauzi on 11/06/21 23.11
 * iqbal.fauzi.if99@gmail.com
 */
//PlaceholderType
enum class PlaceholderType {
    CUSTOM,
    LIGHT,
    DARK
}

// Coil Image Loader
fun ImageView.loadImage(
    imageUrl: String?,
    isUsePlaceholder: Boolean = true,
    placeholderType: PlaceholderType = PlaceholderType.DARK,
    placeholder: Int = 0,
    cornerRadius: Float = 0f
) {
    this.load(imageUrl) {
        transformations(RoundedCornersTransformation(cornerRadius))
        crossfade(true)
        if (isUsePlaceholder) {
            when (placeholderType) {
                PlaceholderType.CUSTOM -> placeholder(placeholder)
                PlaceholderType.LIGHT -> placeholder(createCircularProgressDrawableLight(context))
                PlaceholderType.DARK -> placeholder(createCircularProgressDrawable(context))
            }
        }
        error(R.drawable.ic_image)
    }
}

fun ImageView.loadImage(
    imageBitmap: Bitmap?,
    isUsePlaceholder: Boolean = true,
    placeholderType: PlaceholderType = PlaceholderType.DARK,
    placeholder: Int = 0
) {
    this.load(imageBitmap) {
        crossfade(true)
        if (isUsePlaceholder) {
            when (placeholderType) {
                PlaceholderType.CUSTOM -> placeholder(placeholder)
                PlaceholderType.LIGHT -> placeholder(createCircularProgressDrawableLight(context))
                PlaceholderType.DARK -> placeholder(createCircularProgressDrawable(context))
            }
        }
        error(R.drawable.ic_image)
    }
}

fun ImageView.loadImage(
    imageId: Int,
    isUsePlaceholder: Boolean = true,
    placeholderType: PlaceholderType = PlaceholderType.DARK,
    placeholder: Int = 0
) {
    this.load(imageId) {
        crossfade(true)
        if (isUsePlaceholder) {
            when (placeholderType) {
                PlaceholderType.CUSTOM -> placeholder(placeholder)
                PlaceholderType.LIGHT -> placeholder(createCircularProgressDrawableLight(context))
                PlaceholderType.DARK -> placeholder(createCircularProgressDrawable(context))
            }
        }
        error(R.drawable.ic_image)
    }
}

fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).also {
        it.apply {
            setColorSchemeColors(context.getColorCompat(R.color.purple_500))
            strokeWidth = 8f
            centerRadius = 44f
            start()
        }
    }
}

fun createCircularProgressDrawableLight(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).also {
        it.apply {
            setColorSchemeColors(Color.WHITE)
            strokeWidth = 8f
            centerRadius = 44f
            start()
        }
    }
}