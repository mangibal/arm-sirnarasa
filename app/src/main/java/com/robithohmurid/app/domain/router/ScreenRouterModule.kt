package com.robithohmurid.app.domain.router

import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi on 07/06/21 22.24
 * iqbal.fauzi.if99@gmail.com
 */
val screenRouterModule = module {
    single<ScreenRouter> { ScreenRouterImpl(get()) }
}