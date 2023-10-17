package com.project.home.data.api

import com.project.home.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacter(
        @Query("offset") limit: String? = null,
        @Query("name") name: String? = null
    ): CharacterResponse


}