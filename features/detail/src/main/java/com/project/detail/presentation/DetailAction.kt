package com.project.detail.presentation

import android.graphics.drawable.Drawable
import com.project.commons.stateconfig.BaseEvent

sealed class DetailAction : BaseEvent() {
    data class ShowError(
        val message: String,
        val imageError: Drawable?
    ) : DetailAction()

    object ShowEmptyState : DetailAction()
}