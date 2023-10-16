package com.project.detail.data.model

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("data") val data: DataResponse
)

data class DataResponse(
    @SerializedName("results") val results: List<ResultsResponse>? = null
)

data class ResultsResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse? = null,
    @SerializedName("comics") val comics: CommicsResponse? = null
)

data class ThumbnailResponse(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
)

data class CommicsResponse(
    @SerializedName("items") val itemsResponse: List<ItemsResponse>
)

data class ItemsResponse(
    @SerializedName("resourceURI") val resourceUri: String? = null,
    @SerializedName("name") val name: String? = null
)