package nl.ahmed.data.storage.implementation.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl

@Dao
internal interface BooksDaoImpl : BooksDao {

    override suspend fun getBooks(): List<BookEntity> {
        return _getBooks()
    }

    override suspend fun getBook(bookId: BookEntity.Id): BookEntity {
        return _getBook(bookId = bookId)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun insertBooks(books: List<BookEntity>) {
        _insterBooks(books as List<BookEntityImpl>)
    }

    override suspend fun deleteAll() {
        _deleteAll()
    }

    @Query("SELECT * FROM books")
    suspend fun _getBooks(): List<BookEntityImpl>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun _getBook(bookId: BookEntity.Id): BookEntityImpl

    @Insert
    suspend fun _insterBooks(books: List<BookEntityImpl>)

    @Query("DELETE FROM books")
    suspend fun _deleteAll()
}
