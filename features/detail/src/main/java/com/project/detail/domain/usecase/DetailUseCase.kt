package com.project.detail.domain.usecase

import com.project.detail.domain.model.DetailModel
import com.project.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow

internal class DetailUseCase(private val repository: DetailRepository) {
    fun invoke(id: String): Flow<DetailModel> = repository.getDetailCharacter(id)
}