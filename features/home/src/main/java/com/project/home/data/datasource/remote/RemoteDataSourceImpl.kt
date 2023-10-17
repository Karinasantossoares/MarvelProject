package com.project.home.data.datasource.remote

import com.project.home.data.api.HomeService
import com.project.home.data.model.CharacterResponse

internal class RemoteDataSourceImpl(private val service: HomeService) : RemoteDataSource {

    override suspend fun getAllCharacters(
        incrementPage: String?,
        name: String?
    ): CharacterResponse = service.getAllCharacter(incrementPage, name)
}


