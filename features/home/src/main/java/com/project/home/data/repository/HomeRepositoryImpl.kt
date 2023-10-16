package com.project.home.data.repository

import com.project.home.data.datasource.HomeDataSource
import com.project.home.domain.model.CharacterModel
import com.project.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class HomeRepositoryImpl(private val dataSource: HomeDataSource) : HomeRepository {

    override fun getAllCharacter(incrementPage: String?, name: String?): Flow<CharacterModel> {
        return dataSource.getAllCharacters(incrementPage,name)    }
}