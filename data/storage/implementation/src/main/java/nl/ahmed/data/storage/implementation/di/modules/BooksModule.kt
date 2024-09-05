package nl.ahmed.data.storage.implementation.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.templates.kotlin.Dao
import nl.ahmed.data.storage.api.BookDatabase
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl

@Module(includes = [BooksModule.BooksProvidersModule::class, BooksModule.BooksBindersModule::class])
internal interface BooksModule {

    @Module
    class BooksProvidersModule {
        @Singleton
        @Provides
        fun providesBooksDao(
            bookDatabase: BookDatabase
        ): BooksDao = bookDatabase.booksDao()

        @Singleton
        @Provides
        fun providesBookEntityFactory(): BookEntity.Factory = BookEntityImpl.FactoryImpl()
    }

    @Module
    interface BooksBindersModule {
        @Singleton
        @Binds
        fun bindsQueryDao(
            booksDao: BooksDao
        ): Dao.Query<BookEntity>

        @Singleton
        @Binds
        fun bindsUpdateDao(
            booksDao: BooksDao
        ): Dao.Update<BookEntity>

        @Singleton
        @Binds
        fun bindsDeleteDao(
            booksDao: BooksDao
        ): Dao.Delete<BookEntity>
    }

}
