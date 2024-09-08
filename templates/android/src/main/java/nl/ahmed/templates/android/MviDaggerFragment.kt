package nl.ahmed.templates.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider
import nl.ahmed.common.android.ViewModelFactory
import nl.ahmed.templates.kotlin.mvi.Intent
import nl.ahmed.templates.kotlin.mvi.Navigator
import nl.ahmed.templates.kotlin.mvi.ScreenState
import nl.ahmed.templates.kotlin.mvi.SideEffect

abstract class MviDaggerFragment<SS: ScreenState, I: Intent, SE: SideEffect, N: Navigator> : Fragment() {

    @Inject
    internal lateinit var composeScreenProvider: Provider<BaseComposeScreen<SS, I, SE, N>>

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MviViewModel<SS, I, SE>>
    private val viewModel by viewModels<MviViewModel<SS, I, SE>> { viewModelFactory }

    override fun onAttach(context: Context) {
        injectFragment()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            composeScreenProvider.get().RootView(viewModel = viewModel)
        }
    }

    protected fun handleIntent(intent: I) {
        viewModel.internalHandleIntent(intent)
    }

    abstract fun injectFragment()
}
