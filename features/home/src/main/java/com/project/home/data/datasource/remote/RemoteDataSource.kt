package com.project.home.data.datasource.remote

import com.project.home.data.model.CharacterResponse

internal interface RemoteDataSource {
    suspend fun getAllCharacters(incrementPage: String?, name: String?): CharacterResponse
}