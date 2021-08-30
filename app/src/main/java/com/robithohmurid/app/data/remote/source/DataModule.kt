package com.robithohmurid.app.data.remote.source

import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi on 11/06/21 23.57
 * iqbal.fauzi.if99@gmail.com
 */
val dataModule = module {
    factory<DataSource> { DataSourceImpl(get()) }
}