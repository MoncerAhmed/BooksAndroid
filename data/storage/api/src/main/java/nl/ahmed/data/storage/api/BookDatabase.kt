package nl.ahmed.data.storage.api

import nl.ahmed.data.storage.api.daos.BooksDao

interface BookDatabase {
    fun booksDao(): BooksDao
}
