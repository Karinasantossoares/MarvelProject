package com.project.home.stub

import com.project.home.data.model.CharacterResponse
import com.project.home.data.model.DataResponse
import com.project.home.data.model.ResultsResponse
import com.project.home.data.model.ThumbnailResponse
import com.project.home.domain.model.ResultsModel
import com.project.home.domain.model.ThumbnailModel
import com.project.persistence.model.ResultsEntity
import com.project.persistence.model.ThumbnailEntity

fun getResponseStub(): CharacterResponse {
    return CharacterResponse(
        data = DataResponse(
            results = listOf(
                ResultsResponse(
                    id = "123",
                    name = "Hulk",
                    thumbnail = ThumbnailResponse(
                        path = "teste",
                        extension = ".jpg"
                    )
                )
            )
        )
    )
}

fun getEntityStub(): List<ResultsEntity> {
    return listOf(
        ResultsEntity(
            id = "123",
            name = "Hulk",
            thumbnail = ThumbnailEntity(
                path = "teste",
                extension = ".jpg"
            ),
            page = 0
        )
    )
}

fun getModelStub(id: String = "123"): List<ResultsModel> {
    return listOf(
        ResultsModel(
            id = id,
            name = "Hulk",
            thumbnail = ThumbnailModel(
                path = "teste",
                extension = ".jpg"
            )
        )
    )
}