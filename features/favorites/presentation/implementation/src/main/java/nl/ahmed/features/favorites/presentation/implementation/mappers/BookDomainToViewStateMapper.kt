package nl.ahmed.features.favorites.presentation.implementation.mappers

import javax.inject.Inject
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.templates.kotlin.data.Mapper

internal class BookDomainToViewStateMapper @Inject constructor(): Mapper<FavoriteBook, BookCardViewState> {
    override fun invoke(favoriteBook: FavoriteBook): BookCardViewState {
        return BookCardViewState(
            id = favoriteBook.id.value,
            coverUrl = favoriteBook.coverUrl,
            title = favoriteBook.title,
            author = favoriteBook.author,
            reads = favoriteBook.reads.toString(),
            reviews = favoriteBook.reviews.toString(),
            isFavorite = true
        )
    }
}
