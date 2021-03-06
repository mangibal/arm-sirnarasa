package com.robithohmurid.app.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.model.entity.MenuEntity
import com.robithohmurid.app.databinding.ItemGridMenuBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.onClick

/**
 * Created by Iqbal Fauzi on 4/20/21 10:45 AM
 * iqbal.fauzi.if99@gmail.com
 */
class MenuGridAdapter :
    BaseAdapter<MenuEntity, ItemGridMenuBinding, MenuGridAdapter.ItemHolder>(
        ItemGridMenuBinding::inflate
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGridMenuBinding.inflate(inflater, parent, false)
        return getViewHolder(binding, viewType)
    }

    inner class ItemHolder(private val binding: ItemGridMenuBinding) :
        BaseHolder<MenuEntity>(binding) {

        override fun bind(data: MenuEntity) {
            with(binding) {
                ivIcon.setImageDrawable(root.context.getDrawableCompat(data.icon))
                tvTitle.text = data.name
                cvIcon.onClick {
                    listenerContainData.invoke(data)
                }
            }
        }
    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ItemHolder {
        return ItemHolder(view as ItemGridMenuBinding)
    }
}