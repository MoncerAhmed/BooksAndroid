package nl.ahmed.features.home.data.mappers

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.features.home.domain.api.models.HomeBook

@FragmentScope
internal class BookDalToHomeBookMapper @Inject constructor() {
    fun map(bookData: BookData, isFavorite: Boolean): HomeBook = HomeBook(
        id = HomeBook.Id(bookData.id.value),
        coverUrl = bookData.coverUrl,
        author = bookData.author,
        title = bookData.title,
        createdAt = bookData.createdAt,
        reads = bookData.reads,
        reviews = bookData.reviews,
        isFavorite = isFavorite
    )
}
