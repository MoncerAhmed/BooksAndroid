package nl.ahmed.features.home.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.features.home.presentation.api.HomeScreenState

internal class HomeScreenPreviewParameterProvider : PreviewParameterProvider<HomeScreenState> {
    override val values: Sequence<HomeScreenState> = sequenceOf(
        HomeScreenState.Loaded(
            searchKeyword = "",
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
                ),
            )
        )
    )
}
