package nl.ahmed.features.favorites.ui.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.favorites.presentation.api.FavoritesIntent
import nl.ahmed.features.favorites.presentation.api.FavoritesScreenState
import nl.ahmed.features.favorites.presentation.api.FavoritesSideEffect
import nl.ahmed.features.favorites.ui.FavoritesNavigator
import nl.ahmed.features.favorites.ui.FavoritesScreen
import nl.ahmed.templates.android.BaseComposeScreen

@Module
abstract class FavoritesUiModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsFavoritesScreen(
        favoritesScreen: FavoritesScreen
    ) : BaseComposeScreen<FavoritesScreenState, FavoritesIntent, FavoritesSideEffect, FavoritesNavigator>
}
