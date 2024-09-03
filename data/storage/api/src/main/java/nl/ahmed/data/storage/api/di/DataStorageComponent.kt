package nl.ahmed.data.storage.api.di

import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.daos.ReviewsDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.ReviewEntity

interface DataStorageComponent {
    fun booksDao(): BooksDao

    fun bookEntityFactory(): BookEntity.Factory

    fun reviewsDao(): ReviewsDao

    fun reviewEntityFactory(): ReviewEntity.Factory
}
