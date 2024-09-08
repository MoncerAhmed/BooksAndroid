package nl.ahmed.features.favorites.domain.api.repositories

import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook

interface FavoriteBooksRepository {
    suspend fun getFavoriteBooks(): OperationResult<List<FavoriteBook>>
}
