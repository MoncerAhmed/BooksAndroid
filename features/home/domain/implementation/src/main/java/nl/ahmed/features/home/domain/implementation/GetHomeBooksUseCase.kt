package nl.ahmed.features.home.domain.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.domain.api.repositories.HomeBooksRepository
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class GetHomeBooksUseCase @Inject constructor(
    private val homeBooksRepository: HomeBooksRepository
) : UseCase<@JvmSuppressWildcards String, @JvmSuppressWildcards OperationResult<List<HomeBook>>> {
    override suspend fun invoke(input: String): OperationResult<List<HomeBook>> {
        return homeBooksRepository.getHomeBooks(keyword = input)
    }
}
