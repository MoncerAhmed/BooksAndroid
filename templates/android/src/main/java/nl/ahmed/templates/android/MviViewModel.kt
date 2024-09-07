package nl.ahmed.templates.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nl.ahmed.templates.kotlin.mvi.Intent
import nl.ahmed.templates.kotlin.mvi.ScreenState
import nl.ahmed.templates.kotlin.mvi.SideEffect

abstract class MviViewModel<SS: ScreenState, I: Intent, SE: SideEffect>(
    initialScreenState: SS
) : ViewModel() {
    private val _screenState = MutableStateFlow(initialScreenState)
    val screenState: StateFlow<SS> = _screenState

    private val _sideEffect = Channel<SE>()
    val sideEffect = _sideEffect.receiveAsFlow()

    internal fun internalHandleIntent(intent: I) {
        viewModelScope.launch { onIntent(intent) }
    }

    protected fun handleIntent(intent: I) {
        internalHandleIntent(intent)
    }

    protected suspend fun emitSideEffect(sideEffect: SE) {
        _sideEffect.send(sideEffect)
    }

    protected suspend fun updateScreenState(reducer: (SS) -> SS) {
        _screenState.emit(reducer(screenState.value))
    }

    protected abstract suspend fun onIntent(intent: I)
}
