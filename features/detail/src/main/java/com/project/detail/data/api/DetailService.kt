package com.project.detail.data.api

import com.project.detail.data.model.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DetailService {

    @GET("/v1/public/characters/{id}")
    suspend fun getDetailCharacter(@Path(value = "id") uuid: String) : DetailResponse
}