package nl.ahmed.designsystem.composables.book.models

import androidx.compose.runtime.Stable

@Stable
data class BookCardViewState(
    val id: String,
    val title: String,
    val coverUrl: String,
    val author: String,
    val reads: String,
    val reviews: String,
    val isFavorite: Boolean
)
