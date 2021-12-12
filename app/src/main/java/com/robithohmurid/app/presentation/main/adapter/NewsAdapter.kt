package com.robithohmurid.app.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.local.NewsEntity
import com.robithohmurid.app.databinding.ItemNewsBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.app.getDrawableCompat
import com.robithohmurid.app.external.extension.view.margin
import com.robithohmurid.app.external.extension.view.onClick

/**
 * Created by Iqbal Fauzi on 4/20/21 10:45 AM
 * iqbal.fauzi.if99@gmail.com
 */
class NewsAdapter :
    BaseAdapter<NewsEntity, ItemNewsBinding, NewsAdapter.ItemHolder>(
        ItemNewsBinding::inflate
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return getViewHolder(binding, viewType)
    }

    inner class ItemHolder(private val binding: ItemNewsBinding) :
        BaseHolder<NewsEntity>(binding) {

        override fun bind(data: NewsEntity) {
            with(binding) {
                if (adapterPosition == mListItems.lastIndex) {
                    root.margin(right = 8F)
                }
                ivNews.setImageDrawable(root.context.getDrawableCompat(data.imageUrl))
                tvTitle.text = data.title
                tvDate.text = data.date
                root.onClick {
                    listenerContainData.invoke(data)
                }
            }
        }
    }

    override fun getViewHolder(view: ViewBinding, viewType: Int): ItemHolder {
        return ItemHolder(view as ItemNewsBinding)
    }
}