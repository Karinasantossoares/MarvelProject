package com.project.home.domain.model

data class CharacterModel(
   val data: DataModel
)

data class DataModel(
    val results: List<ResultsModel>
)

data class ResultsModel(
    val id: String,
    val name: String,
    val thumbnail: ThumbnailModel
)

data class ThumbnailModel(
    val path: String,
    val extension: String
)