package nl.ahmed.data.storage.implementation.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nl.ahmed.templates.kotlin.data.Model
import nl.ahmed.data.storage.api.daos.FavoritesDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.FavoriteEntity
import nl.ahmed.data.storage.implementation.entities.BookEntityImpl
import nl.ahmed.data.storage.implementation.entities.FavoriteEntityImpl

@Dao
internal interface FavoriteDaoImpl : FavoritesDao {
    override suspend fun getAll(): List<BookEntityImpl> {
        return _get()
    }

    override suspend fun get(id: Model.Entity.Id): BookEntity {
        return _get(id.value)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun insert(items: List<FavoriteEntity>) {
        _insert(items as List<FavoriteEntityImpl>)
    }

    override suspend fun insert(item: FavoriteEntity) {
        _insert(item as FavoriteEntityImpl)
    }

    override suspend fun isFavorite(bookId: BookEntity.Id): Boolean {
        return _isFavorite(id = bookId.value)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun delete(vararg items: FavoriteEntity) {
        _delete(items.map { it.id })
    }

    override suspend fun deleteAll() {
        _deleteAll()
    }

    @Query("SELECT books.* FROM books, favorites WHERE books.id = favorites.id")
    suspend fun _get(): List<BookEntityImpl>

    @Query("SELECT books.* FROM books, favorites WHERE books.id = favorites.id AND favorites.id = :id")
    suspend fun _get(id: String): BookEntityImpl

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun _insert(items: List<FavoriteEntityImpl>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun _insert(item: FavoriteEntityImpl)

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE id = :id)")
    suspend fun _isFavorite(id: String): Boolean

    @Query("DELETE FROM favorites WHERE id IN (:itemIds)")
    suspend fun _delete(itemIds: List<BookEntity.Id>)

    @Query("DELETE FROM favorites")
    suspend fun _deleteAll()
}
