package nl.ahmed.features.home.shared

import nl.ahmed.core.api.di.CoreComponentProvider
import nl.ahmed.data.dal.di.DataDalComponentProvider
import nl.ahmed.features.home.shared.di.DaggerHomeComponent
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.features.home.ui.HomeNavigator
import nl.ahmed.navigation.di.AppNavigatorComponentProvider
import nl.ahmed.templates.android.MviDaggerFragment

internal class HomeFragment : MviDaggerFragment<HomeScreenState, HomeIntent, HomeSideEffect, HomeNavigator>() {
    override fun injectFragment() {
        DaggerHomeComponent
            .builder()
            .withCoreComponent((requireActivity().application as CoreComponentProvider).getCoreComponent())
            .withAppNavigatorComponent((requireActivity() as AppNavigatorComponentProvider).getAppNavigatorComponent())
            .withDataDalComponent((requireActivity().application as DataDalComponentProvider).getDataDalComponent())
            .build()
            .inject(this)
    }
}
