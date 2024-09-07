package nl.ahmed.templates.android

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import javax.inject.Inject
import nl.ahmed.templates.kotlin.mvi.Intent
import nl.ahmed.templates.kotlin.mvi.Navigator
import nl.ahmed.templates.kotlin.mvi.ScreenState
import nl.ahmed.templates.kotlin.mvi.SideEffect

abstract class BaseComposeScreen<SS: ScreenState, I: Intent, SE: SideEffect, N: Navigator> {

    @Inject
    internal lateinit var _navigator: N
    protected val navigator by lazy { _navigator }

    private val launchedSideEffectKey = "SIDE_EFFECT_KEY"

    @Composable
    internal fun RootView(viewModel: MviViewModel<SS, I, SE>) {
        val state by viewModel.screenState.collectAsState()
        Screen(screenState = state) { viewModel.internalHandleIntent(it) }

        val context = LocalContext.current
        LaunchedEffect(key1 = launchedSideEffectKey) {
            viewModel.sideEffect.collect {
                with(PerformSideEffectScope(context)) {
                    performSideEffect(it)
                }
            }
        }
    }

    @Composable
    protected abstract fun Screen(screenState: SS, intentExecutor: (I) -> Unit)

    context(PerformSideEffectScope)
    protected abstract suspend fun performSideEffect(sideEffect: SE)

    protected class PerformSideEffectScope(val context: Context)
}
