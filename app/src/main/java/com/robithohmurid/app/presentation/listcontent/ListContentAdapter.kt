package com.robithohmurid.app.presentation.listcontent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.viewbinding.ViewBinding
import com.robithohmurid.app.data.model.ListingContentEntity
import com.robithohmurid.app.databinding.ItemListContentBinding
import com.robithohmurid.app.domain.abstraction.BaseAdapter
import com.robithohmurid.app.domain.abstraction.BaseHolder
import com.robithohmurid.app.external.extension.view.onClick
import java.util.*
import kotlin.collections.ArrayList

class ListContentAdapter :
    BaseAdapter<ListingContentEntity, ItemListContentBinding, ListContentAdapter.ItemHolder>(
        ItemListContentBinding::inflate
    ), Filterable {
    var contentFilterList: ArrayList<ListingContentEntity> = arrayListOf()

    inner class ItemHolder(private val binding: ItemListContentBinding) :
        BaseHolder<ListingContentEntity>(binding) {
        override fun bind(data: ListingContentEntity) {
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

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch = constraint.toString()
            contentFilterList = if (charSearch.isEmpty()) {
                mListItems as ArrayList<ListingContentEntity>
            } else {
                val resultList = ArrayList<ListingContentEntity>()
                for (row in mListItems) {
                    if (row.title.lowercase(Locale.getDefault()).contains(
                            constraint.toString()
                                .lowercase(Locale.getDefault())
                        )
                    ) {
                        resultList.add(row)
                    }
                }
                resultList
            }
            val filterResults = FilterResults()
            filterResults.values = contentFilterList
            return filterResults
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val temp = arrayListOf<ListingContentEntity>()
            (results?.values as ArrayList<*>).forEach {
                if(it!=null){
                    temp.add(it as ListingContentEntity)
                }
            }
            contentFilterList = temp
            notifyDataSetChanged()
        }
    }

}