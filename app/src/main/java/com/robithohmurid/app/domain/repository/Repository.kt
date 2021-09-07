package com.robithohmurid.app.domain.repository

import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.remote.source.DataCallback
import com.robithohmurid.app.data.remote.source.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Iqbal Fauzi on 11/06/21 23.59
 * iqbal.fauzi.if99@gmail.com
 */
class Repository(private val dataSource: DataSource) : IRepository {

    override suspend fun getListContent(
        category: String,
        content: String,
        dataCallback: DataCallback<List<ContentEntity>>
    ) {
        try {
            dataSource.getListContent(category, content)
                .flowOn(Dispatchers.IO)
                .collect {
                    dataCallback.onSuccess(it.data ?: emptyList())
                }
        } catch (t: Throwable) {
            dataCallback.onFailure(t)
        }
    }

    override suspend fun getListItem(
        category: String,
        content: String,
        item: String,
        dataCallback: DataCallback<List<ItemEntity>>
    ) {
        try {
            dataSource.getListItem(category, content, item)
                .flowOn(Dispatchers.IO)
                .collect {
                    dataCallback.onSuccess(it.data ?: emptyList())
                }
        } catch (t: Throwable) {
            dataCallback.onFailure(t)
        }
    }
}