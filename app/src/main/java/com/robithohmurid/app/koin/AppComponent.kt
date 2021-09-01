package com.robithohmurid.app.koin

import com.robithohmurid.app.data.remote.network.networkModule
import com.robithohmurid.app.data.remote.source.dataModule
import com.robithohmurid.app.domain.repository.repositoryModule
import com.robithohmurid.app.domain.router.screenRouterModule
import com.robithohmurid.app.external.firebase.firebaseModule
import org.koin.core.module.Module

/**
 * Created by Iqbal Fauzi on 03/06/21 20.47
 * iqbal.fauzi.if99@gmail.com
 */
val appModules: List<Module> = listOf(
    /**
     * Data
     * */
    dataModule,
    repositoryModule,
    /**
     * Network
     * */
    networkModule,
    /**
     * Page Navigation Router
     * */
    screenRouterModule,
    /**
     * Services
     * */
    prayerTimeModule,
    firebaseModule,
    /**
     * ViewModel
     * */
    viewModelModule
)