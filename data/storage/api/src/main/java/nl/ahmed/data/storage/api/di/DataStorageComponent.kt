package nl.ahmed.data.storage.api.di

import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.daos.ReviewsDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.ReviewEntity

interface DataStorageComponent {
    fun booksDao(): BooksDao

    fun booksQueryDao(): Dao.Query<BookEntity>

    fun booksInsertDao(): Dao.Insert<BookEntity>

    fun booksDeleteDao(): Dao.Delete<BookEntity>

    fun bookEntityFactory(): BookEntity.Factory

    fun reviewsDao(): ReviewsDao

    fun reviewEntityFactory(): ReviewEntity.Factory
}
