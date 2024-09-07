package nl.ahmed.feature.home.shared.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.feature.home.shared.HomeNavigatorImpl
import nl.ahmed.features.home.ui.HomeNavigator

@Module
internal interface HomeSharedModule {
    @FragmentScope
    @Binds
    fun bindsHomeNavigator(
        homeNavigatorImpl: HomeNavigatorImpl
    ): HomeNavigator
}
