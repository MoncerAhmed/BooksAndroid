package nl.ahmed.data.storage.api

import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.daos.FavoritesDao

interface BookDatabase {
    fun booksDao(): BooksDao
    fun favoritesDao(): FavoritesDao
}
