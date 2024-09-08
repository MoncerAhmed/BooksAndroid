package nl.ahmed.features.favorites.shared.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.favorites.shared.FavoritesNavigatorImpl
import nl.ahmed.features.favorites.ui.FavoritesNavigator

@Module
internal interface FavoritesSharedModule {
    @FragmentScope
    @Binds
    fun bindsFavoritesNavigator(
        favoritesNavigatorImpl: FavoritesNavigatorImpl
    ): FavoritesNavigator
}
