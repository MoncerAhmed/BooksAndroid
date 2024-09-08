package nl.ahmed.features.favorites.presentation.api

import nl.ahmed.templates.kotlin.mvi.SideEffect

interface FavoritesSideEffect : SideEffect {
    data class NavigateToDetails(val bookId: String) : FavoritesSideEffect
}
