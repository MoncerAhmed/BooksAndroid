package nl.ahmed.dal.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.templates.kotlin.data.Mapper
import nl.ahmed.dal.implementation.repositories.BooksRepositoryImpl
import nl.ahmed.dal.implementation.mappers.BookDtoListToEntityListMapper
import nl.ahmed.dal.implementation.mappers.BookDtoToEntityMapper
import nl.ahmed.dal.implementation.mappers.BookEntityListToDataListMapper
import nl.ahmed.dal.implementation.mappers.BookEntityToDataMapper
import nl.ahmed.dal.implementation.repositories.FavoritesRepositoryImpl
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.BooksRepository
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.storage.api.entities.BookEntity

@Module
internal interface DataDalModule {
    @Binds
    fun bindsBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository

    @Binds
    fun bindsFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository

    @Binds
    fun bindsBookDtoToEntityMapper(
        bookDtoToEntityMapper: BookDtoToEntityMapper
    ): Mapper<BookDto, BookEntity>

    @Binds
    fun bindsBookDtoListToEntityListMapper(
        bookDtoListToEntityListMapper: BookDtoListToEntityListMapper
    ): Mapper<List<BookDto>, List<BookEntity>>

    @Binds
    fun bindsBookEntityToDataMapper(
        bookEntityToDataMapper: BookEntityToDataMapper
    ): Mapper<BookEntity, BookData>

    @Binds
    fun bindsBookEntityListToDataListMapper(
        bookEntityListToDataListMapper: BookEntityListToDataListMapper
    ): Mapper<List<BookEntity>, List<BookData>>
}
