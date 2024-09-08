package nl.ahmed.features.details.presentation.api

import nl.ahmed.templates.kotlin.mvi.Intent

sealed interface DetailsIntent : Intent {
    data class Initialized(val bookId: String) : DetailsIntent

    data object FavoriteButtonClick : DetailsIntent

    data object BackButtonClick : DetailsIntent
}
