package nl.ahmed.data.storage.implementation.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.ahmed.common.kotlin.templates.Model
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl

@Dao
internal interface BooksDaoImpl : BooksDao {

    override suspend fun getAll(): List<BookEntity> {
        return _getBooks()
    }

    override suspend fun get(id: Model.Entity.Id): BookEntity {
        return _getBook(bookId = id.value)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun insert(items: List<BookEntity>) {
        return _insterBooks(items as List<BookEntityImpl>)
    }

    override suspend fun insert(item: BookEntity) {
        return _insterBook(item as BookEntityImpl)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun delete(vararg items: BookEntity) {
        _delete(*(items as Array<out BookEntityImpl>))
    }

    override suspend fun deleteAll() {
        _deleteAll()
    }

    @Query("SELECT * FROM books")
    suspend fun _getBooks(): List<BookEntityImpl>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun _getBook(bookId: String): BookEntityImpl

    @Insert
    suspend fun _insterBooks(books: List<BookEntityImpl>)

    @Insert
    suspend fun _insterBook(book: BookEntityImpl)

    @Delete
    suspend fun _delete(vararg books: BookEntityImpl)

    @Query("DELETE FROM books")
    suspend fun _deleteAll()
}
