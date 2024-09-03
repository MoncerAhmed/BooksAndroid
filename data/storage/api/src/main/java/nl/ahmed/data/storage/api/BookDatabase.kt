package nl.ahmed.data.storage.api

import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.daos.ReviewsDao

interface BookDatabase {
    fun booksDao(): BooksDao
    fun reviewsDao(): ReviewsDao
}
