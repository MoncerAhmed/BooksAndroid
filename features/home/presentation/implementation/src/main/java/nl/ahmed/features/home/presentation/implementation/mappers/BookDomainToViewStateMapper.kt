package nl.ahmed.features.home.presentation.implementation.mappers

import javax.inject.Inject
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.templates.kotlin.data.Mapper

internal class BookDomainToViewStateMapper @Inject constructor(): Mapper<HomeBook, BookCardViewState> {
    override fun invoke(homeBook: HomeBook): BookCardViewState {
        return BookCardViewState(
            id = homeBook.id.value,
            coverUrl = homeBook.coverUrl,
            title = homeBook.title,
            author = homeBook.author,
            reads = homeBook.reads.toString(),
            reviews = homeBook.reviews.toString(),
            isFavorite = homeBook.isFavorite
        )
    }
}
