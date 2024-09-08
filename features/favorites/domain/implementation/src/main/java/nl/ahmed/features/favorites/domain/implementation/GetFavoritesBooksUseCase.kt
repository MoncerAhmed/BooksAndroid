package nl.ahmed.features.favorites.domain.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.features.favorites.domain.api.repositories.FavoriteBooksRepository
import nl.ahmed.templates.kotlin.domain.NoArgUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class GetFavoritesBooksUseCase @Inject constructor(
    private val favoriteBooksRepository: FavoriteBooksRepository
) : NoArgUseCase<@JvmSuppressWildcards OperationResult<List<FavoriteBook>>> {
    override suspend fun invoke(): OperationResult<List<FavoriteBook>> {
        return favoriteBooksRepository.getFavoriteBooks()
    }
}
