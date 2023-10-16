package com.project.detail.data.datasource

import com.project.commons.errorutils.error.ConnectionException
import com.project.commons.errorutils.error.HttpException
import com.project.detail.data.api.DetailService
import com.project.detail.data.mapper.toModel
import com.project.detail.domain.model.DetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException

internal class GetDetailDataSourceImpl(private val detailService: DetailService) :
    GetDetailDataSource {
    override fun getDetailCharacters(id: String): Flow<DetailModel> = flow {
        emit(detailService.getDetailCharacter(id).toModel())
    }.catch { error ->
        throw when (error) {
            is IOException -> ConnectionException
            else -> HttpException
        }
    }
}