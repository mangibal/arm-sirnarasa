package com.robithohmurid.app.data.remote.source

import com.robithohmurid.app.data.model.base.DataResponse
import com.robithohmurid.app.data.model.MenuEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Iqbal Fauzi on 11/06/21 23.54
 * iqbal.fauzi.if99@gmail.com
 */
interface DataSource {
    suspend fun getListContent(category: String, content: String): Flow<DataResponse<List<ItemEntity>>>
    suspend fun getListMenu(): Flow<DataResponse<List<MenuEntity>>>
}