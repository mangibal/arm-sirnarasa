package com.robithohmurid.app.presentation.sholat.jadwal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.entity.SholatEntity
import com.robithohmurid.app.databinding.ItemSholatBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.app.getColorCompat
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.onClick

/**
 * Created by Iqbal Fauzi on 4/20/21 10:45 AM
 * iqbal.fauzi.if99@gmail.com
 */
class SholatAdapter :
    BaseAdapter<SholatEntity, ItemSholatBinding, SholatAdapter.ItemHolder>(
        ItemSholatBinding::inflate
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSholatBinding.inflate(inflater, parent, false)
        return getViewHolder(binding, viewType)
    }

    inner class ItemHolder(private val binding: ItemSholatBinding) :
        BaseHolder<SholatEntity>(binding) {

        override fun bind(data: SholatEntity) {
            with(binding) {
                tvTitle.apply {
                    setTextColor(
                        if (data.isNotification) {
                            context.getColorCompat(R.color.colorPrimary)
                        } else context.getColorCompat(
                            R.color.gray
                        )
                    )
                    text = data.name
                }
                tvTime.text = data.time
                val drawable = if (data.isNotification) {
                    root.context.getDrawableCompat(R.drawable.ic_alarm_on)
                } else {
                    root.context.getDrawableCompat(R.drawable.ic_alarm_off)
                }
                ivNotif.setImageDrawable(drawable)

                root.onClick {
                    listenerContainData.invoke(data)
                }
            }
        }
    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ItemHolder {
        return ItemHolder(view as ItemSholatBinding)
    }
}