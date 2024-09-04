package nl.ahmed.data.storage.api.di

import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.daos.FavoritesDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.FavoriteEntity

interface DataStorageComponent {
    fun booksDao(): BooksDao

    fun bookEntityFactory(): BookEntity.Factory

    fun favoritesDao(): FavoritesDao

    fun favoriteEntityFactory(): FavoriteEntity.Factory

    fun booksQueryDao(): Dao.Query<BookEntity>

    fun booksUpdateDao(): Dao.Update<BookEntity>

    fun booksDeleteDao(): Dao.Delete<BookEntity>
}
