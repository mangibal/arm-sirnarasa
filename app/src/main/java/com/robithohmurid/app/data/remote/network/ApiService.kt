package com.robithohmurid.app.data.remote.network

import com.robithohmurid.app.data.model.base.DataResponse
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.model.response.ContentEntity
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Iqbal Fauzi on 11/06/21 21.35
 * iqbal.fauzi.if99@gmail.com
 */
interface ApiService {

    @GET("amaliyah/{category}/{content}")
    suspend fun getListContent(
        @Path("category") category: String,
        @Path("content") content: String
    ): DataResponse<List<ContentEntity>>

    @GET("amaliyah/{category}/{content}/{item}")
    suspend fun getlistItem(
        @Path("category") category: String,
        @Path("content") content: String,
        @Path("item") item: String,
    ): DataResponse<List<ItemEntity>>

}