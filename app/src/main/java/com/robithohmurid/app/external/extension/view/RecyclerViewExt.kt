package com.robithohmurid.app.external.extension.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Iqbal Fauzi on 11/06/21 23.15
 * iqbal.fauzi.if99@gmail.com
 */
fun Int.dpToPx(rs: Resources): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        rs.displayMetrics
    )
}

fun Float.dpToPx(rs: Resources): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        rs.displayMetrics
    )
}

fun Context.setupList(recyclerView: RecyclerView, isVertical: Boolean = true) {
    recyclerView.apply {
        setHasFixedSize(true)
        layoutManager = if (isVertical) {
            this@setupList.verticalLayout()
        } else this@setupList.horizontalLayout()
        itemAnimator = DefaultItemAnimator()
    }
}

fun Context.setupGridList(recyclerView: RecyclerView, span: Int) {
    recyclerView.apply {
        layoutManager = this@setupGridList.gridLayout(span)
        setHasFixedSize(true)
        itemAnimator = DefaultItemAnimator()
    }
}

fun Context.gridLayout(span: Int): GridLayoutManager = GridLayoutManager(this, span)

fun Context.verticalLayout(): LinearLayoutManager =
    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

fun Context.horizontalLayout(): LinearLayoutManager =
    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

class ListMarginItemDecoration(
    private val spaceSize: Int,
    private val leftSize: Int,
    private val rightSize: Int,
    private val bottomSize: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize.dpToPx(view.resources).toInt()
            }
            left = leftSize.dpToPx(view.resources).toInt()
            right = rightSize.dpToPx(view.resources).toInt()
            bottom = bottomSize.dpToPx(view.resources).toInt()
        }
    }
}

class NewGridMarginItemDecoration(
    private val spaceSize: Float,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    return
                }
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = spaceSize.dpToPx(view.resources).toInt()
                    top = spaceSize.dpToPx(view.resources).toInt()
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = spaceSize.dpToPx(view.resources).toInt()
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = spaceSize.dpToPx(view.resources).toInt()
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = spaceSize.dpToPx(view.resources).toInt()
                }
            }
            right = spaceSize.dpToPx(view.resources).toInt()
            bottom = spaceSize.dpToPx(view.resources).toInt()
        }
    }
}

class GridItemDecorator(
    val context: Context,
    private val spacingDp: Int,
    private val mGridSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val resources = context.resources
        val spacingPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            spacingDp.toFloat(),
            resources.displayMetrics
        )

        val bit = if (spacingPx > mGridSize) Math.round(spacingPx / mGridSize) else 1
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

        outRect.top = if (itemPosition < mGridSize) 0 else bit * mGridSize
        outRect.bottom = 0

        val rowPosition = itemPosition % mGridSize
        outRect.left = rowPosition * bit
        outRect.right = (mGridSize - rowPosition - 1) * bit

    }
}

class MarginItemDecoration(
    private val spaceSize: Int,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = spaceSize
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = spaceSize
                }
            }

            right = spaceSize
            bottom = spaceSize
        }
    }
}

class GridMarginItemDecoration(
    private val spaceSize: Int,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = spaceSize
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = spaceSize
                }
            }
            right = spaceSize
            bottom = spaceSize
        }
    }
}