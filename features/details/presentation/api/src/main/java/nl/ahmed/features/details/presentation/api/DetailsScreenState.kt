package nl.ahmed.features.details.presentation.api

import nl.ahmed.templates.kotlin.mvi.ScreenState

sealed interface DetailsScreenState : ScreenState {

    data object Loading : DetailsScreenState

    data class Loaded(
        val createdAt: String,
        val title: String,
        val author: String,
        val coverUrl: String,
        val description: String,
        val reads: String,
        val reviews: String,
        val summary: String,
        val isFavorite: Boolean
    ) : DetailsScreenState
}
