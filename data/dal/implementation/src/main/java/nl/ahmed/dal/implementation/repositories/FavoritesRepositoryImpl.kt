package nl.ahmed.dal.implementation.repositories

import javax.inject.Inject
import nl.ahmed.common.kotlin.operation.OperationExecutor
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.dal.implementation.mappers.BookEntityToDataMapper
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.data.storage.api.daos.FavoritesDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.FavoriteEntity

internal class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao,
    private val favoriteEntityFactory: FavoriteEntity.Factory,
    private val bookEntityToDataMapper: BookEntityToDataMapper,
    private val operationExecutor: OperationExecutor
) : FavoritesRepository {
    override suspend fun addFavorite(bookId: BookData.Id): OperationResult<Unit> {
        return operationExecutor.execute {
            val favorite = favoriteEntityFactory.create(BookEntity.Id(bookId.value))
            favoritesDao.insert(favorite)
            OperationResult.Success(data = Unit)
        }
    }

    override suspend fun getFavorites(): OperationResult<List<BookData>> {
        return operationExecutor.execute {
            val favorites = favoritesDao.getAll().map(bookEntityToDataMapper)
            OperationResult.Success(favorites)
        }
    }

    override suspend fun isFavorite(bookId: BookData.Id): OperationResult<Boolean> {
        return operationExecutor.execute {
            val result = favoritesDao.isFavorite(bookId = BookEntity.Id(bookId.value))
            OperationResult.Success(result)
        }
    }

    override suspend fun deleteFavorite(bookId: BookData.Id): OperationResult<Unit> {
        return operationExecutor.execute {
            favoritesDao.delete(favoriteEntityFactory.create(id = BookEntity.Id(bookId.value)))
            OperationResult.Success(Unit)
        }
    }
}
