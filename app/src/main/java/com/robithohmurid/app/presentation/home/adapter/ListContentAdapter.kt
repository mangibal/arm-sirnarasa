package com.robithohmurid.app.presentation.home.adapter

import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.model.ContentEntity
import com.robithohmurid.app.databinding.ItemContentBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder

class ListContentAdapter :
    BaseAdapter<ContentEntity, ItemContentBinding, ListContentAdapter.ListContentViewHolder>(
        ItemContentBinding::inflate
    ) {

    inner class ListContentViewHolder(private val binding: ItemContentBinding) :
        BaseHolder<ContentEntity>(binding) {
        override fun bind(data: ContentEntity) {
            binding.tvContent.text = data.title
        }

    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ListContentViewHolder {
        return ListContentViewHolder(view as ItemContentBinding)
    }
}