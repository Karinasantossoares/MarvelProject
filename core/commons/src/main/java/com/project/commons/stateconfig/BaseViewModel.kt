package com.project.commons.stateconfig

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<State, Event>(
    private val state: State
) : ViewModel() {

    val stateLiveData = MutableStateFlow(state)
    val eventLiveData = MutableStateFlow(BaseEvent())


    fun updateState(newState: (State) -> State) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveData.emit(newState.invoke(state))
        }
    }

    fun BaseEvent.run() {
        viewModelScope.launch(Dispatchers.Main) {
            eventLiveData.emit(this@run)
        }
    }
}