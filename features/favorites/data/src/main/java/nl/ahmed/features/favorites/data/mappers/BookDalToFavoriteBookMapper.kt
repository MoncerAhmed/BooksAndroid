package nl.ahmed.features.favorites.data.mappers

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.templates.kotlin.data.Mapper

@FragmentScope
internal class BookDalToFavoriteBookMapper @Inject constructor() : Mapper<BookData, FavoriteBook> {
    override fun invoke(bookData: BookData): FavoriteBook = FavoriteBook(
        id = FavoriteBook.Id(bookData.id.value),
        coverUrl = bookData.coverUrl,
        author = bookData.author,
        title = bookData.title,
        createdAt = bookData.createdAt,
        reads = bookData.reads,
        reviews = bookData.reviews
    )
}
