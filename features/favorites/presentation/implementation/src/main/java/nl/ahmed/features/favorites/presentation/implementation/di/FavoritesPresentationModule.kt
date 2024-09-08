package nl.ahmed.features.favorites.presentation.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.favorites.presentation.api.FavoritesIntent
import nl.ahmed.features.favorites.presentation.api.FavoritesScreenState
import nl.ahmed.features.favorites.presentation.api.FavoritesSideEffect
import nl.ahmed.features.favorites.presentation.implementation.FavoritesViewModel
import nl.ahmed.templates.android.MviViewModel

@Module
abstract class FavoritesPresentationModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsFavoritesViewModel(
        favoritesViewModel: FavoritesViewModel
    ): MviViewModel<FavoritesScreenState, FavoritesIntent, FavoritesSideEffect>
}
