package nl.ahmed.features.details.shared

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import nl.ahmed.core.api.di.CoreComponentProvider
import nl.ahmed.data.dal.di.DataDalComponentProvider
import nl.ahmed.features.details.presentation.api.DetailsIntent
import nl.ahmed.features.details.presentation.api.DetailsScreenState
import nl.ahmed.features.details.presentation.api.DetailsSideEffect
import nl.ahmed.features.details.shared.di.DaggerDetailsComponent
import nl.ahmed.features.details.ui.DetailsNavigator
import nl.ahmed.navigation.di.AppNavigatorComponentProvider
import nl.ahmed.templates.android.MviDaggerFragment

internal class DetailsFragment : MviDaggerFragment<DetailsScreenState, DetailsIntent, DetailsSideEffect, DetailsNavigator>() {

    private val navArgs by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleIntent(DetailsIntent.Initialized(navArgs.bookId))
    }

    override fun injectFragment() {
        DaggerDetailsComponent
            .builder()
            .withCoreComponent((requireActivity().application as CoreComponentProvider).getCoreComponent())
            .withAppNavigatorComponent((requireActivity() as AppNavigatorComponentProvider).getAppNavigatorComponent())
            .withDataDalComponent((requireActivity().application as DataDalComponentProvider).getDataDalComponent())
            .build()
            .inject(this)
    }
}
