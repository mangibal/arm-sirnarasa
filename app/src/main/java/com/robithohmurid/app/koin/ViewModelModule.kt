package com.robithohmurid.app.koin

import com.robithohmurid.app.presentation.content.ContentViewModel
import com.robithohmurid.app.presentation.home.HomeViewModel
import com.robithohmurid.app.presentation.listcontent.ListContentViewModel
import com.robithohmurid.app.presentation.onboarding.OnBoardingViewModel
import com.robithohmurid.app.presentation.settings.SettingsViewModel
import com.robithohmurid.app.presentation.sholat.jadwal.JadwalSholatViewModel
import com.robithohmurid.app.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi on 03/06/21 20.49
 * iqbal.fauzi.if99@gmail.com
 */
val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { OnBoardingViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { JadwalSholatViewModel() }
    viewModel { ListContentViewModel(get()) }
    viewModel { ContentViewModel(get()) }
}