package nl.ahmed.features.favorites.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.ScreenState

sealed interface FavoritesScreenState : ScreenState {
    data object Empty : FavoritesScreenState

    data object Loading : FavoritesScreenState

    data class Loaded(
        val bookCardsViewStates: List<BookCardViewState>
    ) : FavoritesScreenState
}
