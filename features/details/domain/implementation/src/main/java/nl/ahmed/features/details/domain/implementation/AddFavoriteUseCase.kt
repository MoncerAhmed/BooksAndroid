package nl.ahmed.features.details.domain.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.features.details.domain.api.models.BookDetails
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class AddFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : UseCase<BookDetails.Id, OperationResult<Unit>> {
    override suspend fun invoke(input: BookDetails.Id): OperationResult<Unit> {
        return favoritesRepository.addFavorite(bookId = BookData.Id(input.value))
    }
}
