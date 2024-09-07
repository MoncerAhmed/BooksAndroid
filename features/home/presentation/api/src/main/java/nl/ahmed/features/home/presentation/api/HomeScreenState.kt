package nl.ahmed.features.home.presentation.api

import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.templates.kotlin.mvi.ScreenState

sealed interface HomeScreenState : ScreenState {
    val searchKeyword: String

    fun copyWithSearchKeyword(searchKeyword: String): HomeScreenState

    data class Empty(override val searchKeyword: String) : HomeScreenState {
        override fun copyWithSearchKeyword(searchKeyword: String): Empty {
            return copy(searchKeyword = searchKeyword)
        }
    }

    data class Loading(override val searchKeyword: String) : HomeScreenState {
        override fun copyWithSearchKeyword(searchKeyword: String): Loading {
            return copy(searchKeyword = searchKeyword)
        }
    }

    data class Loaded(
        override val searchKeyword: String,
        val bookCardsViewStates: List<BookCardViewState>
    ) : HomeScreenState {
        override fun copyWithSearchKeyword(searchKeyword: String): Loaded {
            return copy(searchKeyword = searchKeyword)
        }
    }
}
