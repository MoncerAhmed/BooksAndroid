package nl.ahmed.features.details.presentation.api

import nl.ahmed.templates.kotlin.mvi.SideEffect

interface DetailsSideEffect : SideEffect {
    data object NavigateBack : DetailsSideEffect
}
