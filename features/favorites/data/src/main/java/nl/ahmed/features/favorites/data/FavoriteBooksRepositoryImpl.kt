package nl.ahmed.features.favorites.data

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.mapIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.features.favorites.data.mappers.BookDalToFavoriteBookMapper
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.features.favorites.domain.api.repositories.FavoriteBooksRepository

@FragmentScope
internal class FavoriteBooksRepositoryImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val bookDalToFavoriteBookMapper: BookDalToFavoriteBookMapper
) : FavoriteBooksRepository {
    override suspend fun getFavoriteBooks(): OperationResult<List<FavoriteBook>> {
        return favoritesRepository
            .getFavorites()
            .mapIfSuccessful { favoriteBooksData ->
                favoriteBooksData.map(bookDalToFavoriteBookMapper)
            }
    }
}
