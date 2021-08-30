package com.robithohmurid.app.domain.repository

import org.koin.dsl.module

/**
 * Created by Iqbal Fauzi on 12/06/21 00.02
 * iqbal.fauzi.if99@gmail.com
 */
val repositoryModule = module {
    single<IRepository> { Repository(get()) }
}