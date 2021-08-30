package com.robithohmurid.app.data.remote.source

/**
 * Created by Iqbal Fauzi on 11/06/21 23.52
 * iqbal.fauzi.if99@gmail.com
 */
interface DataCallback<T> {
    fun onSuccess(data: T)
    fun onFailure(t: Throwable)
}