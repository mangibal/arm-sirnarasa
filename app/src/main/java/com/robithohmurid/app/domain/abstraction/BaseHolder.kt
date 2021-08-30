package com.robithohmurid.app.domain.abstraction

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Iqbal Fauzi on 4/20/21 11:04 AM
 * iqbal.fauzi.if99@gmail.com
 */
abstract class BaseHolder<M>(binder: ViewBinding) : RecyclerView.ViewHolder(binder.root),
    BaseAdapter.Binder<M> {}