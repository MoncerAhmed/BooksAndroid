package nl.ahmed.features.home.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.Intent

sealed interface HomeIntent : Intent {
    data object Initialized : HomeIntent

    data class ItemClick(val bookCardViewState: BookCardViewState) : HomeIntent

    data class SearchKeywordChange(val newKeyword: String) : HomeIntent

    data object ClearSearchKeyword : HomeIntent
}
