package nl.ahmed.features.details.shared.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.details.shared.DetailsNavigatorImpl
import nl.ahmed.features.details.ui.DetailsNavigator

@Module
internal interface DetailsSharedModule {
    @FragmentScope
    @Binds
    fun bindsFavoritesNavigator(
        favoritesNavigatorImpl: DetailsNavigatorImpl
    ): DetailsNavigator
}
