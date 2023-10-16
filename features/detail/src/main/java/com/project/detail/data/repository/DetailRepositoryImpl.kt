package com.project.detail.data.repository

import com.project.detail.data.datasource.GetDetailDataSource
import com.project.detail.domain.model.DetailModel
import com.project.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow

internal class DetailRepositoryImpl(private val dataSource: GetDetailDataSource) :
    DetailRepository {
    override fun getDetailCharacter(id: String): Flow<DetailModel> {
        return dataSource.getDetailCharacters(id)
    }
}