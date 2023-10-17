package com.project.home.data.repository

import com.project.commons.errorutils.error.ConnectionException
import com.project.commons.errorutils.error.HttpException
import com.project.home.data.datasource.local.LocalDataSource
import com.project.home.data.datasource.remote.RemoteDataSource
import com.project.home.data.mapper.toListEntity
import com.project.home.data.mapper.toModel
import com.project.home.domain.model.ResultsModel
import com.project.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException

internal class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : HomeRepository {

    override fun getAllCharacter(incrementPage: String?, name: String?): Flow<List<ResultsModel>> {
        val currentPage = incrementPage?.toInt() ?: 0
        return flow {
            val remoteData = remoteDataSource.getAllCharacters(
                incrementPage,
                name
            ).toModel().data.results
            localDataSource.saveCharacter(remoteData.toListEntity(currentPage))
            emit(remoteData)
        }.catch { exception ->
            val localData = localDataSource.getAllCharacters(currentPage).map { it.toModel() }
            if (localData.isNotEmpty()) {
                emit(localData)
            } else {
                throw when (exception) {
                    is IOException -> ConnectionException
                    else -> HttpException
                }
            }
        }
    }
}