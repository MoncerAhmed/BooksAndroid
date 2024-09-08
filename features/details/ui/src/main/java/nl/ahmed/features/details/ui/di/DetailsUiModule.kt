package nl.ahmed.features.details.ui.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.details.presentation.api.DetailsIntent
import nl.ahmed.features.details.presentation.api.DetailsScreenState
import nl.ahmed.features.details.presentation.api.DetailsSideEffect
import nl.ahmed.features.details.ui.DetailsNavigator
import nl.ahmed.features.details.ui.DetailsScreen
import nl.ahmed.templates.android.BaseComposeScreen

@Module
abstract class DetailsUiModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsDetailsScreen(
        detailsScreen: DetailsScreen
    ) : BaseComposeScreen<DetailsScreenState, DetailsIntent, DetailsSideEffect, DetailsNavigator>
}
