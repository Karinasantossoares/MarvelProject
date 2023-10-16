package com.project.detail.domain.repository

import com.project.detail.domain.model.DetailModel
import kotlinx.coroutines.flow.Flow

internal interface DetailRepository {
    fun getDetailCharacter(id: String): Flow<DetailModel>

}