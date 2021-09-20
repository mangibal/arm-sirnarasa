package com.robithohmurid.app.presentation.listcontent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.model.entity.ListingEntity
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.databinding.ItemListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.view.onClick

class ListContentAdapter :
    BaseAdapter<ListingEntity, ItemListContentBinding, ListContentAdapter.ItemHolder>(
        ItemListContentBinding::inflate
    ) {

    inner class ItemHolder(private val binding: ItemListContentBinding) :
        BaseHolder<ListingEntity>(binding) {
        override fun bind(data: ListingEntity) {
            with(binding) {
                tvTitleContent.text = data.title
                root.onClick { listenerContainData.invoke(data) }
            }
        }
    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ItemHolder {
        return ItemHolder(view as ItemListContentBinding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListContentBinding.inflate(inflater, parent, false)
        return getViewHolder(binding, viewType)
    }

}