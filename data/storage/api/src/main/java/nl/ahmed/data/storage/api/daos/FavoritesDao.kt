package nl.ahmed.data.storage.api.daos

import nl.ahmed.templates.kotlin.data.Dao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.FavoriteEntity

interface FavoritesDao : Dao.Query<BookEntity>, Dao.Insert<FavoriteEntity>, Dao.Delete<FavoriteEntity> {
    suspend fun isFavorite(bookId: BookEntity.Id) : Boolean
}
