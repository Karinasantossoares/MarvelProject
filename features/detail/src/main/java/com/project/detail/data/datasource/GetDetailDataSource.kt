package com.project.detail.data.datasource

import com.project.detail.domain.model.DetailModel
import kotlinx.coroutines.flow.Flow

interface GetDetailDataSource {
    fun getDetailCharacters(id: String): Flow<DetailModel>
}