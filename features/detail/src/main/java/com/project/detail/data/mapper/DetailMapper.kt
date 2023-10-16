package com.project.detail.data.mapper

import com.project.detail.data.model.DetailResponse
import com.project.detail.domain.model.CommicsModel
import com.project.detail.domain.model.DataModel
import com.project.detail.domain.model.DetailModel
import com.project.detail.domain.model.ItemsModel
import com.project.detail.domain.model.ResultsModel
import com.project.detail.domain.model.ThumbnailModel

fun DetailResponse.toModel(): DetailModel {
    return DetailModel(
        data = DataModel(
            results = data.results?.map {
                ResultsModel(
                    name = it.name,
                    thumbnail = ThumbnailModel(
                        extension = it.thumbnail?.extension,
                        path = it.thumbnail?.path
                    ),
                    comics = CommicsModel(
                        itemsModel = it.comics?.itemsResponse?.map { items ->
                            ItemsModel(
                                resourceUri = items.resourceUri,
                                name = items.name
                            )
                        }
                    )
                )
            }
        )
    )
}