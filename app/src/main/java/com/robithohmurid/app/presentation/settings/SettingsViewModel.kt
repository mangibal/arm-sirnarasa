package com.robithohmurid.app.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.robithohmurid.app.data.model.MenuEntity
import com.robithohmurid.app.data.remote.source.DataCallback
import com.robithohmurid.app.domain.abstraction.BaseViewModel
import com.robithohmurid.app.domain.repository.IRepository
import com.robithohmurid.app.external.extension.app.logError
import kotlinx.coroutines.launch

/**
 * Created by Iqbal Fauzi on 03/06/21 21.29
 * iqbal.fauzi.if99@gmail.com
 */
class SettingsViewModel(private val repo: IRepository): BaseViewModel() {

    private val _loadingMenu: MutableLiveData<Boolean> = MutableLiveData()
    val loadingMenu: LiveData<Boolean> = _loadingMenu

    private val _listMenu: MutableLiveData<List<MenuEntity>> = MutableLiveData()
    val listMenu: LiveData<List<MenuEntity>> = _listMenu

}