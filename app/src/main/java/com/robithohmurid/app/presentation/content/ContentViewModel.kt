package com.robithohmurid.app.presentation.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.model.response.ItemEntity
import com.robithohmurid.app.data.remote.source.DataCallback
import com.robithohmurid.app.domain.abstraction.BaseViewModel
import com.robithohmurid.app.domain.repository.IRepository
import com.robithohmurid.app.external.extension.app.logError
import com.robithohmurid.app.external.extension.app.logInfo
import kotlinx.coroutines.launch

class ContentViewModel(private val repo: IRepository) : BaseViewModel() {

    private val _listItem: MutableLiveData<List<ItemEntity>> = MutableLiveData()
    val listItem: LiveData<List<ItemEntity>> = _listItem

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListItem(category: String, content: String, item: String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            repo.getListItem(category, content, item,
                object : DataCallback<List<ItemEntity>> {
                    override fun onSuccess(data: List<ItemEntity>) {
                        _isLoading.postValue(false)
                        _listItem.postValue(data)
                        logInfo(data.toString())
                    }

                    override fun onFailure(t: Throwable) {
                        _isLoading.postValue(false)
                        logError(t.message.toString())
                    }

                })
        }
    }

}