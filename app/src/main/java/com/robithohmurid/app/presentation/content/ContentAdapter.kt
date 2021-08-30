package com.robithohmurid.app.presentation.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.model.ContentEntity
import com.robithohmurid.app.databinding.ItemContentBinding
import com.robithohmurid.app.databinding.ItemListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import io.noties.markwon.Markwon

class ContentAdapter :
    BaseAdapter<ContentEntity, ItemContentBinding, ContentAdapter.ItemHolder>(
        ItemContentBinding::inflate
    ) {
    inner class ItemHolder(private val binding: ItemContentBinding) :
        BaseHolder<ContentEntity>(binding) {

        override fun bind(data: ContentEntity) {
           binding.tvContent.let {
               Markwon.create(it.context).setMarkdown(it,data.content)
           }
        }
    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ItemHolder {
        return ItemHolder(view as ItemContentBinding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContentBinding.inflate(inflater, parent, false)
        return getViewHolder(binding, viewType)
    }
}