package nl.ahmed.designsystem.composables.book.models

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class BookCardViewStatePreviewParameterProvider : PreviewParameterProvider<BookCardViewState> {
    override val values: Sequence<BookCardViewState> = sequenceOf(
        BookCardViewState(
            id = "1",
            coverUrl = "https://loremflickr.com/640/480/abstract",
            title = "Book title",
            author = "Author name",
            reads = "2",
            reviews = "5",
            isFavorite = true
        )
    )
}
