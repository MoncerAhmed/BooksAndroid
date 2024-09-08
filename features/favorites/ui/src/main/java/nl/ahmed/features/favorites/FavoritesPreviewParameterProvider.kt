package nl.ahmed.features.favorites

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.features.favorites.presentation.api.FavoritesScreenState

internal class FavoritesPreviewParameterProvider : PreviewParameterProvider<FavoritesScreenState>{
    override val values: Sequence<FavoritesScreenState> = sequenceOf(
        FavoritesScreenState.Loaded(
            bookCardsViewStates = listOf(
                BookCardViewState(
                    id = "1",
                    title = "Title",
                    author = "Author",
                    coverUrl = "",
                    reads = "2",
                    reviews = "2",
                    isFavorite = false
                ),
                BookCardViewState(
                    id = "1",
                    title = "Title",
                    author = "Author",
                    coverUrl = "",
                    reads = "2",
                    reviews = "2",
                    isFavorite = false
                ),
                BookCardViewState(
                    id = "1",
                    title = "Title",
                    author = "Author",
                    coverUrl = "",
                    reads = "2",
                    reviews = "2",
                    isFavorite = false
                ),
                BookCardViewState(
                    id = "1",
                    title = "Title",
                    author = "Author",
                    coverUrl = "",
                    reads = "2",
                    reviews = "2",
                    isFavorite = false
                )
            )
        )
    )
}
