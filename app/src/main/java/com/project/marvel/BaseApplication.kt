package com.project.marvel

import android.app.Application
import com.project.detail.di.DetailModule
import com.project.home.di.HomeModule
import com.project.marvel.di.NavigationModule
import com.project.network.di.NetworkModule
import com.project.persistence.PersistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                NetworkModule.networkModules(BuildConfig.BASE_URL),
                HomeModule.homeModules(),
                NavigationModule.module(),
                DetailModule.detailModules(),
                PersistenceModule.persistenceModule()
            )
            allowOverride(true)
        }
    }
}
