package com.project.home.domain.usecase

import com.project.home.domain.model.CharacterModel
import com.project.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class HomeUseCase(private val repository: HomeRepository) {
    fun invoke(incrementPage: String?,name:String?): Flow<CharacterModel> {
        return repository.getAllCharacter(incrementPage,name)
    }
}