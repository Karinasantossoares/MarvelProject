package com.project.home.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data") val data: DataResponse
)

data class DataResponse(
    @SerializedName("results") val results: List<ResultsResponse>? = null
)

data class ResultsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String? = null,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse? = null
)

data class ThumbnailResponse(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
)