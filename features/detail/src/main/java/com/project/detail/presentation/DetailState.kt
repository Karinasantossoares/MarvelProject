package com.project.detail.presentation

import com.project.detail.domain.model.DetailModel

data class DetailState(
    val isLoading: Boolean = false,
    val detailModel: DetailModel? = null,
    val urlPhoto: String? = "",
    val description:String =""
) {

    fun setLoading(value: Boolean) = copy(isLoading = value)
    fun setSuccess(detailModel: DetailModel, urlPhoto: String, description: String) = copy(
        detailModel = detailModel,
        urlPhoto = urlPhoto,
        description = description
    )


}