package com.project.persistence

import org.koin.dsl.module


object PersistenceModule {
    fun persistenceModule() = module {
        single {AppDataBase.instance(get()) }
        single { get<AppDataBase>().characterDao() }
    }
}
