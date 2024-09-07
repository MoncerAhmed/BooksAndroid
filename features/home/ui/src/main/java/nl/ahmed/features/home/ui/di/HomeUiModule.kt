package nl.ahmed.features.home.ui.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.features.home.ui.HomeNavigator
import nl.ahmed.features.home.ui.HomeScreen
import nl.ahmed.templates.android.BaseComposeScreen

@Module
abstract class HomeUiModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsHomeScreen(
        homeScreen: HomeScreen
    ) : BaseComposeScreen<HomeScreenState, HomeIntent, HomeSideEffect, HomeNavigator>
}
