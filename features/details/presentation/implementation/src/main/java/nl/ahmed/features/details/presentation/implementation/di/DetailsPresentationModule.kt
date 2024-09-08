package nl.ahmed.features.details.presentation.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.details.presentation.api.DetailsIntent
import nl.ahmed.features.details.presentation.api.DetailsScreenState
import nl.ahmed.features.details.presentation.api.DetailsSideEffect
import nl.ahmed.features.details.presentation.implementation.DetailsViewModel
import nl.ahmed.templates.android.MviViewModel

@Module
abstract class DetailsPresentationModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsDetailsViewModel(
        detailsViewModel: DetailsViewModel
    ): MviViewModel<DetailsScreenState, DetailsIntent, DetailsSideEffect>
}
