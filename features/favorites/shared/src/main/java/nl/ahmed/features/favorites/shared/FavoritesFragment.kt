package nl.ahmed.features.favorites.shared

import nl.ahmed.core.api.di.CoreComponentProvider
import nl.ahmed.data.dal.di.DataDalComponentProvider
import nl.ahmed.features.favorites.presentation.api.FavoritesIntent
import nl.ahmed.features.favorites.presentation.api.FavoritesScreenState
import nl.ahmed.features.favorites.presentation.api.FavoritesSideEffect
import nl.ahmed.features.favorites.shared.di.DaggerFavoritesComponent
import nl.ahmed.features.favorites.ui.FavoritesNavigator
import nl.ahmed.navigation.di.AppNavigatorComponentProvider
import nl.ahmed.templates.android.MviDaggerFragment

internal class FavoritesFragment : MviDaggerFragment<FavoritesScreenState, FavoritesIntent, FavoritesSideEffect, FavoritesNavigator>() {
    override fun injectFragment() {
        DaggerFavoritesComponent
            .builder()
            .withCoreComponent((requireActivity().application as CoreComponentProvider).getCoreComponent())
            .withAppNavigatorComponent((requireActivity() as AppNavigatorComponentProvider).getAppNavigatorComponent())
            .withDataDalComponent((requireActivity().application as DataDalComponentProvider).getDataDalComponent())
            .build()
            .inject(this)
    }
}
