package nl.ahmed.features.home.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.SideEffect

interface HomeSideEffect : SideEffect {
    data class NavigateToDetails(val bookCardViewState: BookCardViewState): HomeSideEffect
}
