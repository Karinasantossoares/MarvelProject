package com.project.home.data.datasource

import com.project.home.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

internal interface HomeDataSource {
    fun getAllCharacters(incrementPage: String?, name: String?): Flow<CharacterModel>
}