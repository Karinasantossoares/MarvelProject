package com.project.home.di

import com.project.home.data.api.HomeService
import com.project.home.data.datasource.HomeDataSource
import com.project.home.data.datasource.HomeDataSourceImpl
import com.project.home.data.repository.HomeRepositoryImpl
import com.project.home.domain.repository.HomeRepository
import com.project.home.domain.usecase.HomeUseCase
import com.project.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {
    fun homeModules() = module {
        factory { get<Retrofit>().create(HomeService::class.java) }
        factory { HomeUseCase(repository = get()) }
        factory<HomeRepository> { HomeRepositoryImpl(dataSource = get()) }
        factory<HomeDataSource> { HomeDataSourceImpl(service = get()) }
        viewModel {
            HomeViewModel(useCase = get(), application = get())
        }
    }
}
