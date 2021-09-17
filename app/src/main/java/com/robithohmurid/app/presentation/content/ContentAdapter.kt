package com.robithohmurid.app.presentation.content

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.R
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.databinding.ItemContentBinding
import com.robithohmurid.app.databinding.ItemListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.app.copyText
import com.robithohmurid.app.external.extension.app.showToast
import com.robithohmurid.app.external.extension.view.onLongClick
import io.noties.markwon.Markwon

class ContentAdapter :
    BaseAdapter<ItemEntity, ItemListContentBinding, ContentAdapter.ItemHolder>(
        ItemListContentBinding::inflate
    ) {
    inner class ItemHolder(private val binding: ItemContentBinding) :
        BaseHolder<ItemEntity>(binding) {

        val tvContent = binding.tvContent

        override fun bind(data: ItemEntity) {
            with(binding) {
                tvContent.apply {
                    isEnabled = true
                    isFocusable = true
                    setTextIsSelectable(true)
                    Markwon.create(context).setMarkdown(this, data.body)
//                    onLongClick {
//                        this.context.copyText("Text telah di-copy", data.body)
//                        this.context.showToast("Text telah di-copy", Toast.LENGTH_LONG)
//                        return@onLongClick true
//                    }
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: ItemHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder.absoluteAdapterPosition == 0) {
            holder.tvContent.apply {
                isEnabled = true
                isFocusable = true
                setTextIsSelectable(true)
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