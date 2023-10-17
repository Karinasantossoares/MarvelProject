package com.project.home.presentation

import android.graphics.drawable.Drawable
import com.project.commons.stateconfig.BaseEvent

sealed class HomeAction : BaseEvent() {
    data class ShowError(
        val message: String,
        val imageError: Drawable?
    ) : HomeAction()

    data class NavigateToDetail(val id: String, val url: String) : HomeAction()

    object ShowEmptyState : HomeAction()
}