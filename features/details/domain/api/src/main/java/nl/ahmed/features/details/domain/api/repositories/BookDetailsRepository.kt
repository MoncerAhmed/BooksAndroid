package nl.ahmed.features.details.domain.api.repositories

import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.details.domain.api.models.BookDetails

interface BookDetailsRepository {
    suspend fun getBookDetails(bookId: BookDetails.Id): OperationResult<BookDetails>
}
