package com.project.home.presentation

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.viewModelScope
import com.project.commons.errorutils.error.ConnectionException
import com.project.commons.stateconfig.BaseEvent
import com.project.commons.stateconfig.BaseViewModel
import com.project.home.domain.model.ResultsModel
import com.project.home.domain.usecase.HomeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


internal class HomeViewModel(
    private val useCase: HomeUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val application: Application,
) : BaseViewModel<HomeState, HomeAction>(HomeState()) {

    private var requestPending = false


    init {
        getAllCharacter(incrementPage = false)
    }


    internal fun getAllCharacter(incrementPage: Boolean = true, nameFilter: String? = null) =
        viewModelScope.launch {
            if (requestPending) return@launch
            requestPending = true
            var currentPage = stateLiveData.value.getNextPage(incrementPage)
            if (!nameFilter.isNullOrEmpty()) {
                currentPage = 0
            }
            useCase.invoke(currentPage.toString(), nameFilter)
                .flowOn(dispatcher)
                .onCompletion {
                    requestPending = false
                    updateState { it.setLoading(false) }
                }
                .onStart { updateState { it.setLoading(true) } }
                .catch { onError(it) }
                .collect {
                    onSuccess(it, nameFilter)
                }
        }


    private fun onError(exception: Throwable) {
        when (exception) {
            is ConnectionException -> {
                HomeAction.ShowError(
                    application.getString(com.project.ui.R.string.internet_error),
                    AppCompatResources.getDrawable(
                        application,
                        com.project.ui.R.drawable.no_connection
                    )
                ).run()
            }

            else -> {
                HomeAction.ShowError(
                    application.getString(com.project.ui.R.string.message_error_generic),
                    AppCompatResources.getDrawable(
                        application,
                        com.project.ui.R.drawable.image_generic_error
                    ),
                ).run()
            }
        }

    }

    private fun onSuccess(list: List<ResultsModel>, nameFilter: String?) {
        if (nameFilter.isNullOrEmpty()) {
            updateState { it.setSuccess(list.toMutableList()) }
        } else {
            if (list.isEmpty()) {
                updateState { it.copy(emptyState = true) }
            } else {
                updateState { it.copy(listCharacter = list.toMutableList()) }
            }
        }
    }

    fun onPagination() {
        if (stateLiveData.value.finishList.not()) {
            getAllCharacter()
        }
    }

    fun onClickSearch(text: String) {
        getAllCharacter(incrementPage = false, nameFilter = text)
    }

    fun clearFilter() {
        stateLiveData.value.listCharacter = mutableListOf()
        getAllCharacter(false, null)
    }

    fun navigateToDetail(id: String, url: String) {
        HomeAction.NavigateToDetail(id, url).run()
    }

    fun clearState() {
        viewModelScope.launch {  eventLiveData.emit(BaseEvent())}
    }

}
