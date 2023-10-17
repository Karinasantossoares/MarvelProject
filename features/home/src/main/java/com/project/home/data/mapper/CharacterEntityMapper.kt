package com.project.home.data.mapper

import com.project.home.domain.model.ResultsModel
import com.project.home.domain.model.ThumbnailModel
import com.project.persistence.model.ResultsEntity
import com.project.persistence.model.ThumbnailEntity

fun List<ResultsModel>.toListEntity(page: Int): List<ResultsEntity> {
    return map {
        ResultsEntity(
            name = it.name,
            id = it.id,
            thumbnail = it.thumbnail.toEntity(),
            page = page
        )
    }
}



fun ResultsEntity.toModel(): ResultsModel {
    return ResultsModel(name = name, id = id, thumbnail = thumbnail.toModel())
}

fun ThumbnailEntity.toModel() = ThumbnailModel(
    path = path,
    extension = extension
)

fun ThumbnailModel.toEntity(): ThumbnailEntity {
    return ThumbnailEntity(
        path = path,
        extension = extension
    )
}