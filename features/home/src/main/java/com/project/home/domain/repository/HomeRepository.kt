package com.project.home.domain.repository

import com.project.home.domain.model.ResultsModel
import kotlinx.coroutines.flow.Flow

internal interface HomeRepository {
    fun getAllCharacter(incrementPage: String?, name: String?): Flow<List<ResultsModel>>
}