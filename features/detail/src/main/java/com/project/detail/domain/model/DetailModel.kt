package com.project.detail.domain.model

import com.google.gson.annotations.SerializedName

data class DetailModel(
    @SerializedName("data") val data: DataModel
)

data class DataModel(
    @SerializedName("results") val results: List<ResultsModel>? = null
)

data class ResultsModel(
    @SerializedName("name") val name: String? = null,
    @SerializedName("thumbnail") val thumbnail: ThumbnailModel? = null,
    @SerializedName("comics") val comics: CommicsModel? = null
)

data class ThumbnailModel(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
)

data class CommicsModel(
    @SerializedName("items") val itemsModel: List<ItemsModel>? = null
)

data class ItemsModel(
    @SerializedName("resourceURI") val resourceUri: String? = null,
    @SerializedName("name") val name: String? = null
)