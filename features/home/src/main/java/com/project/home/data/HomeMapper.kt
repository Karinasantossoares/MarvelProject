package com.project.home.data

import com.project.home.data.model.CharacterResponse
import com.project.home.domain.model.CharacterModel
import com.project.home.domain.model.DataModel
import com.project.home.domain.model.ResultsModel
import com.project.home.domain.model.ThumbnailModel


fun CharacterResponse.toModel(): CharacterModel {
    return CharacterModel(
        data = DataModel(
            results = data.results?.map {
                ResultsModel(
                    id = it.id,
                    name = it.name ?: "",
                    thumbnail = ThumbnailModel(
                        it.thumbnail?.path ?: "",
                        it.thumbnail?.extension ?: ""
                    )
                )
            } ?: emptyList()
        )
    )
}

