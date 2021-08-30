package com.robithohmurid.app.presentation.splash

import com.robithohmurid.app.domain.abstraction.BaseViewModel

/**
 * Created by Iqbal Fauzi on 03/06/21 19.10
 * iqbal.fauzi.if99@gmail.com
 */
class SplashViewModel: BaseViewModel() {
    fun isFirstLaunch() : Boolean = sessionHelper.isOnboarding() ?: true
}
//komenu