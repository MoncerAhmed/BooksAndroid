package nl.ahmed.dal.implementation.mappers

import javax.inject.Inject
import nl.ahmed.templates.kotlin.data.Mapper
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.storage.api.entities.BookEntity

internal class BookDtoToEntityMapper @Inject constructor(
    private val bookEntityFactory: BookEntity.Factory
) : Mapper<BookDto, BookEntity> {
    override fun invoke(bookDto: BookDto): BookEntity  = bookEntityFactory.create(
        id = BookEntity.Id(bookDto.id.value),
        title = bookDto.title,
        createdAt = bookDto.createdAt,
        author = bookDto.author,
        coverUrl = bookDto.coverUrl,
        description = bookDto.description,
        reads = bookDto.reads,
        reviews = bookDto.reviews,
        summary = bookDto.summary
    )
}
