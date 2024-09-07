package nl.ahmed.dal.implementation.mappers

import javax.inject.Inject
import nl.ahmed.templates.kotlin.data.Mapper
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.storage.api.entities.BookEntity

internal class BookEntityListToDataListMapper @Inject constructor(
    private val bookEntityToDataMapper: BookEntityToDataMapper
) : Mapper<@JvmSuppressWildcards List<BookEntity>, @JvmSuppressWildcards List<BookData>> {
    override fun invoke(
        bookEntityList: List<BookEntity>
    ): List<BookData> = bookEntityList.map(bookEntityToDataMapper)
}
