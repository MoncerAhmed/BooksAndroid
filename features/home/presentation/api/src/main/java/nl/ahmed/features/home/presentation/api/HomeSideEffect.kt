package nl.ahmed.features.home.presentation.api

import nl.ahmed.templates.kotlin.mvi.SideEffect

interface HomeSideEffect : SideEffect {
    data class NavigateToDetails(val bookId: String): HomeSideEffect
}
