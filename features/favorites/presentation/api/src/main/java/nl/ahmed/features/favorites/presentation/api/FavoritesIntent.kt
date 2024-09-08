package nl.ahmed.features.favorites.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.Intent

sealed interface FavoritesIntent : Intent {
    data object Initialized : FavoritesIntent

    data class ItemClick(val bookCardViewState: BookCardViewState) : FavoritesIntent

    data class FavoriteButtonClick(val bookCardViewState: BookCardViewState) : FavoritesIntent
}
