package com.robithohmurid.app.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robithohmurid.app.data.model.entity.SettingEntity
import com.robithohmurid.app.domain.abstraction.BaseViewModel
import com.robithohmurid.app.domain.repository.IRepository

/**
 * Created by Iqbal Fauzi on 03/06/21 21.29
 * iqbal.fauzi.if99@gmail.com
 */
class SettingsViewModel(private val repo: IRepository): BaseViewModel() {

    private val _loadingMenu: MutableLiveData<Boolean> = MutableLiveData()
    val loadingMenu: LiveData<Boolean> = _loadingMenu

    private val _listSetting: MutableLiveData<List<SettingEntity>> = MutableLiveData()
    val listSetting: LiveData<List<SettingEntity>> = _listSetting

}