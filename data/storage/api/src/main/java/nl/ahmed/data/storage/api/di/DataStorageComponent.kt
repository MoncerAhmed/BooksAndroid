package nl.ahmed.data.storage.api.di

import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.entities.BookEntity

interface DataStorageComponent {
    fun booksDao(): BooksDao

    fun booksQueryDao(): Dao.Query<BookEntity>

    fun booksInsertDao(): Dao.Insert<BookEntity>

    fun booksDeleteDao(): Dao.Delete<BookEntity>

    fun bookEntityFactory(): BookEntity.Factory
}
