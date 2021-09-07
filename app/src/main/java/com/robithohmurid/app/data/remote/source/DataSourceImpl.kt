package com.robithohmurid.app.data.remote.source

import com.robithohmurid.app.data.local.SessionHelper
import com.robithohmurid.app.data.model.base.DataResponse
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.remote.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Iqbal Fauzi on 11/06/21 23.52
 * iqbal.fauzi.if99@gmail.com
 */
class DataSourceImpl(
    private val apiService: ApiService
) : DataSource {

    private val session = SessionHelper()

    override suspend fun getListContent(
        category: String,
        content: String
    ): Flow<DataResponse<List<ContentEntity>>> {
        return flow {

            // fetch cache data first
            val cache = session.getListData(content)
            val dataCache = DataResponse(cache)
            emit(dataCache)

            //fetch latest update
            val apiResponse = apiService.getListContent(category, content)
            emit(apiResponse)

            if (apiResponse.data != null) {
                session.saveListData(content, apiResponse.data)
            }
        }
    }

    override suspend fun getListItem(
        category: String,
        content: String,
        item: String
    ): Flow<DataResponse<List<ItemEntity>>> {
        return flow {

            // fetch cache data first
            val cache = session.getListItem(item)
            val dataCache = DataResponse(cache)
            emit(dataCache)

            //fetch latest update
            val apiResponse = apiService.getlistItem(category, content, item)
            emit(apiResponse)

            if (apiResponse.data != null) {
                session.saveListItem(item, apiResponse.data)
            }
        }
    }

}