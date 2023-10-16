package com.project.home.data.datasource

import com.project.commons.errorutils.error.ConnectionException
import com.project.commons.errorutils.error.HttpException
import com.project.home.data.api.HomeService
import com.project.home.data.toModel
import com.project.home.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException

internal class HomeDataSourceImpl(private val service: HomeService) : HomeDataSource {

    override fun getAllCharacters(incrementPage: String?, name: String?): Flow<CharacterModel> =
        flow {
            emit(service.getAllCharacter(incrementPage, name).toModel())
        }.catch { error ->
            throw when (error) {
                is IOException -> ConnectionException
                else -> HttpException
            }
        }
}


