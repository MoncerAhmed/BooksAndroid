package nl.ahmed.features.favorites.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.SideEffect

interface FavoritesSideEffect : SideEffect {
    data class NavigateToDetails(val bookCardViewState: BookCardViewState) : FavoritesSideEffect
}
