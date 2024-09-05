package nl.ahmed.dal.implementation.mappers

import javax.inject.Inject
import nl.ahmed.templates.kotlin.Mapper
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.storage.api.entities.BookEntity

internal class BookEntityToDataMapper @Inject constructor() : Mapper<BookEntity, BookData> {
    override fun invoke(bookEntity: BookEntity): BookData = BookData(
        id = BookData.Id(bookEntity.id.value),
        title = bookEntity.title,
        createdAt = bookEntity.createdAt,
        author = bookEntity.author,
        coverUrl = bookEntity.coverUrl,
        description = bookEntity.description,
        reads = bookEntity.reads,
        reviews = bookEntity.reviews,
        summary = bookEntity.summary
    )
}
