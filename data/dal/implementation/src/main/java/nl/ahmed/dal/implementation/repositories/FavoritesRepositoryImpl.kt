package nl.ahmed.dal.implementation.repositories

import javax.inject.Inject
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
    private val bookEntityToDataMapper: BookEntityToDataMapper
) : FavoritesRepository {
    override suspend fun addFavorite(bookId: BookData.Id): OperationResult<Unit> {
        return try {
            val favorite = favoriteEntityFactory.create(BookEntity.Id(bookId.value))
            favoritesDao.insert(favorite)
            OperationResult.Success(data = Unit)
        } catch (e: Exception) {
            OperationResult.Failure(e)
        }
    }

    override suspend fun getFavorites(): OperationResult<List<BookData>> {
        return try {
            val favorites = favoritesDao.getAll().map(bookEntityToDataMapper)
            OperationResult.Success(favorites)
        } catch (e: Exception) {
            OperationResult.Failure(e)
        }
    }

    override suspend fun deleteFavorite(bookId: BookData.Id): OperationResult<Unit> {
        return try {
            favoritesDao.delete(favoriteEntityFactory.create(id = BookEntity.Id(bookId.value)))
            OperationResult.Success(Unit)
        } catch (e: Exception) {
            OperationResult.Failure(e)
        }
    }
}
