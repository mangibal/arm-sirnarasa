package com.robithohmurid.app.domain.repository

import com.robithohmurid.app.data.model.MenuEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.remote.source.DataCallback

/**
 * Created by Iqbal Fauzi on 11/06/21 23.59
 * iqbal.fauzi.if99@gmail.com
 */
interface IRepository {

    suspend fun getListContent(
        category: String,
        content: String,
        dataCallback: DataCallback<List<ItemEntity>>
    )

    suspend fun getListMenu(dataCallback: DataCallback<List<MenuEntity>>)

}