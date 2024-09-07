package nl.ahmed.features.home.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.ScreenState

sealed interface HomeScreenState : ScreenState {
    data object Loading : HomeScreenState

    data class Loaded(
        val bookCardsViewStates: List<BookCardViewState>
    ) : HomeScreenState
}
