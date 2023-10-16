package com.project.detail.di

import com.project.detail.data.api.DetailService
import com.project.detail.data.datasource.GetDetailDataSource
import com.project.detail.data.datasource.GetDetailDataSourceImpl
import com.project.detail.data.repository.DetailRepositoryImpl
import com.project.detail.domain.repository.DetailRepository
import com.project.detail.domain.usecase.DetailUseCase
import com.project.detail.presentation.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object DetailModule {
    fun detailModules() = module {
        factory { get<Retrofit>().create(DetailService::class.java) }
        factory { DetailUseCase(repository = get()) }
        factory<DetailRepository> { DetailRepositoryImpl(dataSource = get()) }
        factory<GetDetailDataSource> { GetDetailDataSourceImpl(detailService = get()) }
        viewModel { (id: String, url: String) ->
            DetailViewModel(
                useCase = get(),
                application = get(),
                id = id,
                urlPhoto = url
            )
        }
    }
}
