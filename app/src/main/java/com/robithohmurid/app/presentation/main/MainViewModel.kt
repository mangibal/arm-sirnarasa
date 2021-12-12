package com.robithohmurid.app.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.remote.source.DataCallback
import com.robithohmurid.app.domain.abstraction.BaseViewModel
import com.robithohmurid.app.domain.repository.IRepository
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.constant.ContentConstant
import com.robithohmurid.app.external.extension.app.logError
import com.robithohmurid.app.external.extension.app.logInfo
import kotlinx.coroutines.launch

/**
 * Created by Iqbal Fauzi on 03/06/21 21.29
 * iqbal.fauzi.if99@gmail.com
 */
class MainViewModel(private val repo: IRepository) : BaseViewModel() {

    private val _loadingMenu: MutableLiveData<Boolean> = MutableLiveData()
    val loadingMenu: LiveData<Boolean> = _loadingMenu

    private val _listContent: MutableLiveData<List<ContentEntity>> = MutableLiveData()
    val listContent: LiveData<List<ContentEntity>> = _listContent

    private val _listItem: MutableLiveData<List<ItemEntity>> = MutableLiveData()
    val listItem: LiveData<List<ItemEntity>> = _listItem

    fun getListSholat() {
        viewModelScope.launch {
            repo.getListContent(
                CategoryConstant.AMALIYAH_KEY,
                ContentConstant.SHOLAT_KEY,
                object : DataCallback<List<ContentEntity>> {
                    override fun onSuccess(data: List<ContentEntity>) {
                        _listContent.postValue(data)
                        logInfo(data.toString())
                    }

                    override fun onFailure(t: Throwable) {
                        logError(t.message.toString())
                    }

                })
        }
    }

    fun getListItem(itemName: String) {
        viewModelScope.launch {
            repo.getListItem(
                CategoryConstant.AMALIYAH_KEY,
                ContentConstant.SHOLAT_KEY,
                itemName,
                object : DataCallback<List<ItemEntity>> {
                    override fun onSuccess(data: List<ItemEntity>) {
                        _listItem.postValue(data)
                        logInfo(data.toString())
                    }

                    override fun onFailure(t: Throwable) {
                        logError(t.message.toString())
                    }

                })
        }
    }

}