package nl.ahmed.features.details.domain.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.mapIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.details.domain.api.models.BookDetails
import nl.ahmed.features.details.domain.api.repositories.BookDetailsRepository
import nl.ahmed.templates.kotlin.domain.NoArgUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class GetBookDetailsUseCase @Inject constructor(
    private val bookDetailsRepository: BookDetailsRepository
) : UseCase<@JvmSuppressWildcards BookDetails.Id, @JvmSuppressWildcards OperationResult<BookDetails>> {
    override suspend fun invoke(input: BookDetails.Id): OperationResult<BookDetails> {
        return bookDetailsRepository.getBookDetails(input).mapIfSuccessful {
            it.copy(
                summary = it.summary.replace("\n", "\n\n")
            )
        }
    }
}
