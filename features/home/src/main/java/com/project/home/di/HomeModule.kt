package com.project.home.di

import com.project.home.data.api.HomeService
import com.project.home.data.datasource.local.LocalDataSource
import com.project.home.data.datasource.local.LocalDataSourceImpl
import com.project.home.data.datasource.remote.RemoteDataSource
import com.project.home.data.datasource.remote.RemoteDataSourceImpl
import com.project.home.data.repository.RemoteRepositoryImpl
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
        factory<HomeRepository> {
            RemoteRepositoryImpl(
                remoteDataSource = get(),
                localDataSource = get()
            )
        }
        factory<RemoteDataSource> { RemoteDataSourceImpl(service = get()) }
        factory<LocalDataSource> { LocalDataSourceImpl(characterDao = get()) }
        viewModel {
            HomeViewModel(useCase = get(), application = get())
        }
    }
}
