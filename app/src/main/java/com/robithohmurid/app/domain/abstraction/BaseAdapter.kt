package com.robithohmurid.app.domain.abstraction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Iqbal Fauzi on 4/20/21 11:04 AM
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseAdapter<T, V : ViewBinding, VH : RecyclerView.ViewHolder>(viewBinder: (LayoutInflater) -> ViewBinding) :
    RecyclerView.Adapter<VH>() {
    var mListItems: List<T>
    var listenerContainData: ((T) -> Unit) = {}
    private val mViewBinding = viewBinder

    init {
        mListItems = emptyList()
    }

    fun setListener(listener: ((T) -> Unit)) {
        this.listenerContainData = listener
    }

    fun setItems(listItems: List<T>?) {
        this.mListItems = listItems ?: listOf()
        notifyDataSetChanged()
    }

    fun refreshRecyclerView() {
        notifyDataSetChanged()
    }

    val isEmpty get() = mListItems.isEmpty()

    override fun getItemCount(): Int = mListItems.size

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = mViewBinding.invoke(inflater) as V
        return getViewHolder(binding, viewType)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: VH, position: Int) {
        (holder as Binder<T>).bind(mListItems[position])
        holder.itemView.tag = mListItems[position]
    }

    abstract fun getViewHolder(view: ViewBinding, viewType: Int): VH

    interface Binder<T> {
        fun bind(data: T)
    }
}