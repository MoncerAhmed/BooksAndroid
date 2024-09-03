package nl.ahmed.books.di

import dagger.Module
import dagger.Provides
import nl.ahmed.books.Book
import nl.ahmed.common.kotlin.Mapper
import nl.ahmed.core.di.AppScope
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.storage.api.entities.BookEntity

@Module
internal class AppModule {
    @AppScope
    @Provides
    fun provideString() = "Hello"

    @AppScope
    @Provides
    fun providesBookDtoToEntityMapper(
        bookEntityFactory: BookEntity.Factory
    ): Mapper<BookDto, BookEntity> = Mapper { bookDto ->
        bookEntityFactory.create(
            id = BookEntity.Id(bookDto.id.value),
            createdAt = bookDto.createdAt,
            author = bookDto.author,
            coverUrl = bookDto.coverUrl,
            description = bookDto.description,
            reads = bookDto.reads,
            reviews = bookDto.reviews,
            summary = bookDto.summary
        )
    }

    @AppScope
    @Provides
    fun providesBookDtoListToEntityListMapper(
        bookDtoToEntityMapper: Mapper<BookDto, BookEntity>
    ): Mapper<List<BookDto>, List<BookEntity>> = Mapper { bookDtos ->
        bookDtos.map { bookDto ->
            bookDtoToEntityMapper(bookDto)
        }
    }

    @AppScope
    @Provides
    fun providesBookEntityToDomainMapper(
    ): Mapper<BookEntity, Book> = Mapper { bookEntity ->
        Book(
            id = bookEntity.id.value
        )
    }

    @AppScope
    @Provides
    fun providesBookEntityListToDomainListMapper(
        bookEntityToDomainMapper: Mapper<BookEntity, Book>
    ): Mapper<List<BookEntity>, List<Book>> = Mapper { bookEntities ->
        bookEntities.map { bookEntity ->
            bookEntityToDomainMapper(bookEntity)
        }
    }
}
