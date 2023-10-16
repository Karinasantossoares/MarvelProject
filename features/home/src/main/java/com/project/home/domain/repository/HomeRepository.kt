package com.project.home.domain.repository

import com.project.home.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

internal interface HomeRepository {
    fun getAllCharacter(incrementPage: String?, name: String?): Flow<CharacterModel>
}