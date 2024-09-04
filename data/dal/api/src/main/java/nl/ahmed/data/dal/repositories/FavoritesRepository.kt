package nl.ahmed.data.dal.repositories

import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData

interface FavoritesRepository {
    suspend fun addFavorite(bookId: BookData.Id): OperationResult<Unit>

    suspend fun getFavorites(): OperationResult<List<BookData>>

    suspend fun deleteFavorite(bookId: BookData.Id): OperationResult<Unit>
}
