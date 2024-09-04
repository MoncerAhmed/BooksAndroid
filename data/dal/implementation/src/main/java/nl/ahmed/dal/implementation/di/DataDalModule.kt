package nl.ahmed.dal.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.templates.Mapper
import nl.ahmed.dal.implementation.BooksRepositoryImpl
import nl.ahmed.dal.implementation.mappers.BookDtoListToEntityListMapper
import nl.ahmed.dal.implementation.mappers.BookEntityListToDataListMapper
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.BooksRepository
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.storage.api.entities.BookEntity

@Module
internal interface DataDalModule {
    @Binds
    fun bindsBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository

    @Binds
    fun bindsBookDtoListToEntityListMapper(
        bookDtoListToEntityListMapper: BookDtoListToEntityListMapper
    ): Mapper<List<BookDto>, List<BookEntity>>

    @Binds
    fun bindsBookEntityListToDataListMapper(
        bookEntityListToDataListMapper: BookEntityListToDataListMapper
    ): Mapper<List<BookEntity>, List<BookData>>
}
