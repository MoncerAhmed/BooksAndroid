package nl.ahmed.dal.implementation.mappers

import javax.inject.Inject
import nl.ahmed.templates.kotlin.data.Mapper
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.storage.api.entities.BookEntity

internal class BookDtoListToEntityListMapper @Inject constructor(
    private val bookDtoToEntityMapper: Mapper<BookDto, BookEntity>
) : Mapper<@JvmSuppressWildcards List<BookDto>, @JvmSuppressWildcards List<BookEntity>> {
    override fun invoke(
        bookDtoList: List<BookDto>
    ): List<BookEntity> = bookDtoList.map(bookDtoToEntityMapper)
}
