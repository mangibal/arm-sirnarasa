package com.robithohmurid.app.external.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toolbar
import coil.load
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.robithohmurid.app.R
import com.robithohmurid.app.databinding.LayoutAppBarViewBinding
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.loadImage
import com.robithohmurid.app.external.extension.view.showIf

/**
 * Created by Iqbal Fauzi on 10/07/21 16.52
 * iqbal.fauzi.if99@gmail.com
 */
class AppBar(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    private lateinit var binding: LayoutAppBarViewBinding
    private lateinit var toolbarListener: ToolbarListener
    lateinit var appToolbar : MaterialToolbar


    private val onAppBarScroll = object : OnOffsetChangedListener {
        var isShow = false
        var scrollRange = -1
        override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                isShow = false
                binding.ivHeader.showIf(isShow)
            } else if (!isShow) {
                isShow = true
                binding.ivHeader.showIf(isShow)
            }
        }
    }

    interface ToolbarListener {
        fun onBackPressed()
    }

    init {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = LayoutAppBarViewBinding.inflate(inflater, this, true)
        initUiComponent()
    }

    private fun initUiComponent() {
        with(binding) {
            toolbar.setNavigationOnClickListener { toolbarListener.onBackPressed() }
            appBar.addOnOffsetChangedListener(onAppBarScroll)
        }
        appToolbar = binding.toolbar
    }

    fun setToolbarListener(listener: ToolbarListener) {
        toolbarListener = listener
    }

    fun setImageDrawable(image: Int) {
        binding.ivHeader.load(image)
    }

    fun setToolbarTitle(title: String){
        binding.toolbar.title = title
    }


}