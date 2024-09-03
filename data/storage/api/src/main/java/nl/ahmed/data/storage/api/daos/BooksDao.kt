package nl.ahmed.data.storage.api.daos

import nl.ahmed.data.storage.api.entities.BookEntity

interface BooksDao {
    suspend fun getBooks(): List<BookEntity>
    suspend fun getBook(bookId: BookEntity.Id): BookEntity
    suspend fun insertBooks(books: List<BookEntity>)
    suspend fun deleteAll()
}
