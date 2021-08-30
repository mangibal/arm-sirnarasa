package com.robithohmurid.app.external.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.robithohmurid.app.databinding.ItemConfigBinding
import com.robithohmurid.app.external.extension.view.onClick
import com.robithohmurid.app.external.extension.view.show
import com.robithohmurid.app.external.extension.view.showIf

/**
 * Created by Iqbal Fauzi on 10/07/21 16.52
 * iqbal.fauzi.if99@gmail.com
 */
class ConfigView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private lateinit var binding: ItemConfigBinding
    private lateinit var listener: OnItemListener

    interface OnItemListener {
        fun onToggleSwitch(isActive: Boolean)
        fun onClickItem()
    }

    init {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ItemConfigBinding.inflate(inflater, this, true)
        setupComponent()
    }

    private fun setupComponent() {
        with(binding) {
            swSettings.onClick {
                listener.onToggleSwitch(swSettings.isChecked)
            }
            root.onClick {
                listener.onClickItem()
            }
        }
    }

    fun setToggleListener(listener: OnItemListener) {
        this.listener = listener
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setSubTitle(subtitle: String) {
        binding.tvSubtitle.apply {
            show()
            text = subtitle
        }
    }

    fun setToggleVisibility(isShow: Boolean) {
        binding.swSettings.showIf(isShow)
    }

}