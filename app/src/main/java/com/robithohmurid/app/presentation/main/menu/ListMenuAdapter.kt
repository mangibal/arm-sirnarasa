package com.robithohmurid.app.presentation.main.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.model.entity.MenuEntity
import com.robithohmurid.app.databinding.ItemListMenuBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.onClick

/**
 * Created by Iqbal Fauzi on 4/20/21 10:45 AM
 * iqbal.fauzi.if99@gmail.com
 */
class ListMenuAdapter :
    BaseAdapter<MenuEntity, ItemListMenuBinding, ListMenuAdapter.ItemHolder>(
        ItemListMenuBinding::inflate
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListMenuBinding.inflate(inflater, parent, false)
        return getViewHolder(binding, viewType)
    }

    inner class ItemHolder(private val binding: ItemListMenuBinding) :
        BaseHolder<MenuEntity>(binding) {

        override fun bind(data: MenuEntity) {
            with(binding) {
                ivIcon.setImageDrawable(root.context.getDrawableCompat(data.icon))
                tvTitle.text = data.name
                tvSubtitle.text = data.subtitle
                root.onClick {
                    listenerContainData.invoke(data)
                }
            }
        }
    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ItemHolder {
        return ItemHolder(view as ItemListMenuBinding)
    }
}