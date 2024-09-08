package nl.ahmed.features.favorites.domain.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.templates.kotlin.domain.UseCase

internal class RemoveFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : UseCase<FavoriteBook.Id, OperationResult<Unit>> {
    override suspend fun invoke(input: FavoriteBook.Id): OperationResult<Unit> {
        return favoritesRepository.deleteFavorite(BookData.Id(input.value))
    }
}
