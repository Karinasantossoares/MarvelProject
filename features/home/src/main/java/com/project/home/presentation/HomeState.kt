package com.project.home.presentation

import com.project.home.domain.model.ResultsModel

data class HomeState(
    val isLoading: Boolean = false,
    var listCharacter: MutableList<ResultsModel> = mutableListOf(),
    var page: Int = 0,
    var finishList: Boolean = false,
    val emptyState: Boolean = false
) {

    fun setLoading(value: Boolean) = copy(isLoading = value)
    fun setSuccess(list: MutableList<ResultsModel>) = copy(
        listCharacter = listCharacter.apply {
            addAll(list)
        }
    )

    fun getNextPage(incrementPage: Boolean): Int {
        return if (incrementPage) ++page else page
    }
}