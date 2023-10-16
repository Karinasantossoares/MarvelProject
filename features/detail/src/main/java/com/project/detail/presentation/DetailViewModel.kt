package com.project.detail.presentation

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.viewModelScope
import com.project.commons.errorutils.error.ConnectionException
import com.project.commons.errorutils.util.BaseViewModel
import com.project.detail.R
import com.project.detail.domain.model.DetailModel
import com.project.detail.domain.usecase.DetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class DetailViewModel(
    private val useCase: DetailUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val application: Application,
    private val id: String,
    private val urlPhoto: String
) : BaseViewModel<DetailState, DetailAction>(DetailState()) {

    init {
        getDetailUseCase()
    }

    private fun getDetailUseCase() = viewModelScope.launch {
        useCase.invoke(id)
            .flowOn(dispatcher)
            .onStart { updateState { it.setLoading(true) } }
            .catch { onError(it) }
            .collectLatest {
                onSuccess(it)
            }
    }

    private fun onSuccess(detailModel: DetailModel) {
        updateState { it.setLoading(false) }
        val description = application.applicationContext.getString(R.string.hint_description)
        updateState { it.setSuccess(detailModel, urlPhoto, description) }
    }

    private fun onError(exception: Throwable) {
        updateState { it.setLoading(false) }
        when (exception) {
            is ConnectionException -> {
                DetailAction.ShowError(
                    application.getString(com.project.ui.R.string.internet_error),
                    AppCompatResources.getDrawable(
                        application,
                        com.project.ui.R.drawable.no_connection
                    )
                ).run()
            }

            else -> {
                DetailAction.ShowError(
                    application.getString(com.project.ui.R.string.message_error_generic),
                    AppCompatResources.getDrawable(
                        application,
                        com.project.ui.R.drawable.image_generic_error
                    ),
                ).run()
            }
        }

    }
}