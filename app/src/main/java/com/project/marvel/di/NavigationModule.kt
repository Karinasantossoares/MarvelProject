package com.project.marvel.di

import com.project.detail.navigation.DetailNavigationImpl
import com.project.home.navigation.HomeNavigationImpl
import com.project.navigation.DetailNavigation
import com.project.navigation.HomeNavigation


object NavigationModule {
    fun module() = org.koin.dsl.module {
        single<HomeNavigation> {
            HomeNavigationImpl()
        }
        single<DetailNavigation> {
            DetailNavigationImpl()
        }
    }
}