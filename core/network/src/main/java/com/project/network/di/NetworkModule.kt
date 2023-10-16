package com.project.network.di

import com.project.network.retrofitconfig.RetrofitConfig
import org.koin.dsl.module

object NetworkModule {
        fun networkModules(baseUrl: String) = module {
            single { RetrofitConfig().createRetrofit(baseUrl) }
    }
}
