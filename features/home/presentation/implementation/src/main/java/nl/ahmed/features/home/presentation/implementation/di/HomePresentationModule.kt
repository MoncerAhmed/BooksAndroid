package nl.ahmed.features.home.presentation.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.features.home.presentation.implementation.HomeViewModel
import nl.ahmed.templates.android.MviViewModel

@Module
abstract class HomePresentationModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsHomeViewModel(
        homeViewModel: HomeViewModel
    ): MviViewModel<HomeScreenState, HomeIntent, HomeSideEffect>
}
