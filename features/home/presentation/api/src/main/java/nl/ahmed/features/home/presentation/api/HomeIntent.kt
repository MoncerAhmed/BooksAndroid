package nl.ahmed.features.home.presentation.api

import nl.ahmed.templates.kotlin.mvi.Intent

sealed interface HomeIntent : Intent {
    data object Initialized : HomeIntent
}
