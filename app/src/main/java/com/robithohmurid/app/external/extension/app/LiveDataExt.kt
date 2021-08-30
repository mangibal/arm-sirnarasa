package com.robithohmurid.app.external.extension.app

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Iqbal Fauzi on 11/06/21 22.57
 * iqbal.fauzi.if99@gmail.com
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { data ->
        data?.let { t -> action(t) }
    })
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { data ->
        data?.let { t -> action(t) }
    })
}