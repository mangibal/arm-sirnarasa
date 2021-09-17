package com.robithohmurid.app.presentation.listcontent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.robithohmurid.app.data.model.response.ContentEntity
import com.robithohmurid.app.data.remote.source.DataCallback
import com.robithohmurid.app.domain.abstraction.BaseViewModel
import com.robithohmurid.app.domain.repository.IRepository
import com.robithohmurid.app.external.constant.CategoryConstant
import com.robithohmurid.app.external.extension.app.logError
import com.robithohmurid.app.external.extension.app.logInfo
import kotlinx.coroutines.launch

class ListContentViewModel(private val repo: IRepository) : BaseViewModel() {

    private val _listContent: MutableLiveData<List<ContentEntity>> = MutableLiveData()
    val listContent: LiveData<List<ContentEntity>> = _listContent

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListContent(category: String, content: String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            repo.getListContent(category, content,
                object : DataCallback<List<ContentEntity>> {
                    override fun onSuccess(data: List<ContentEntity>) {
                        _isLoading.postValue(false)
                        _listContent.postValue(data)
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
