package nl.ahmed.data.storage.implementation.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.data.storage.api.BookDatabase
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl

@Module
internal class BooksModule {
    @Singleton
    @Provides
    fun providesBooksDao(
        bookDatabase: BookDatabase
    ): BooksDao = bookDatabase.booksDao()

    @Singleton
    @Provides
    fun providesBookEntityFactory(): BookEntity.Factory = BookEntityImpl.FactoryImpl()
}
