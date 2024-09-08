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

    val currentScreenState: SS
        get() = screenState.value

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

    protected suspend fun updateScreenState(reducer: suspend (SS) -> SS) {
        _screenState.emit(reducer(screenState.value))
    }

    protected suspend fun updateScreenState(screenState: SS) {
        updateScreenState { screenState }
    }

    protected suspend inline fun <reified S: SS> updateScreenStateIfCurrentIs(crossinline reducer: suspend (S) -> SS) {
        (currentScreenState as? S)?.let { currentSState ->
            updateScreenState { reducer(currentSState) }
        }
    }

    protected suspend inline fun <reified S: SS> doIfCurrentScreenStateIs(crossinline block: suspend (S) -> Unit) {
        (currentScreenState as? S)?.let { currentSState ->
            block(currentSState)
        }
    }

    protected suspend inline fun <reified S: SS> updateScreenStateIfCurrentIsNot(crossinline reducer: (SS) -> SS) {
        if(currentScreenState !is S) {
            updateScreenState { reducer(currentScreenState) }
        }
    }

    protected abstract suspend fun onIntent(intent: I)
}
