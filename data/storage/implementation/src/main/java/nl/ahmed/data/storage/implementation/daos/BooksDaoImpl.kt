package nl.ahmed.data.storage.implementation.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import nl.ahmed.templates.kotlin.Model
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl
import nl.ahmed.data.storage.implementation.entities.FavoriteEntityImpl

@Dao
internal interface BooksDaoImpl : BooksDao {

    override suspend fun getAll(): List<BookEntity> {
        return _get()
    }

    override suspend fun get(id: Model.Entity.Id): BookEntity {
        return _get(bookId = id.value)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun updateAll(items: List<BookEntity>) {
        updateBooksAndFavorites(items as List<BookEntityImpl>)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun delete(vararg items: BookEntity) {
        _delete(*(items as Array<out BookEntityImpl>))
    }

    override suspend fun deleteAll() {
        _deleteAll()
    }

    @Transaction
    suspend fun updateBooksAndFavorites(items: List<BookEntityImpl>) {
        _insert(items)
        val favorites = _getFavorites()
        val toDeleteFavorites = favorites.filter { favorite -> items.none { it.id == favorite.id } }
        _deleteFavorites(*toDeleteFavorites.toTypedArray())
    }

    @Query("SELECT * FROM favorites")
    suspend fun _getFavorites(): List<FavoriteEntityImpl>

    @Delete
    suspend fun _deleteFavorites(vararg favorite: FavoriteEntityImpl)

    @Query("SELECT * FROM books")
    suspend fun _get(): List<BookEntityImpl>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun _get(bookId: String): BookEntityImpl

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun _insert(books: List<BookEntityImpl>)

    @Delete
    suspend fun _delete(vararg books: BookEntityImpl)

    @Query("DELETE FROM books")
    suspend fun _deleteAll()
}
