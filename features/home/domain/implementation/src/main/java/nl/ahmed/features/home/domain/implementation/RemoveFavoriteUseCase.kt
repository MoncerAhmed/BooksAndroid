package nl.ahmed.features.home.domain.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.templates.kotlin.domain.UseCase

internal class RemoveFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : UseCase<HomeBook.Id, OperationResult<Unit>> {
    override suspend fun invoke(input: HomeBook.Id): OperationResult<Unit> {
        return favoritesRepository.deleteFavorite(BookData.Id(input.value))
    }
}
