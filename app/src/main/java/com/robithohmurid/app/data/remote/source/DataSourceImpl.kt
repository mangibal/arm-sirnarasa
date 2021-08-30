package com.robithohmurid.app.data.remote.source

import com.robithohmurid.app.data.model.base.DataResponse
import com.robithohmurid.app.data.model.MenuEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

/**
 * Created by Iqbal Fauzi on 11/06/21 23.52
 * iqbal.fauzi.if99@gmail.com
 */
class DataSourceImpl(private val apiService: ApiService) : DataSource {

    override suspend fun getListContent(
        category: String,
        content: String
    ): Flow<DataResponse<List<ItemEntity>>> {
        return flowOf(apiService.getListContent(category, content))
    }

    override suspend fun getListMenu(): Flow<DataResponse<List<MenuEntity>>> {
        return flowOf(apiService.getListMenu()).flowOn(Dispatchers.IO)
    }

}