package nl.ahmed.dal.implementation.repositories

import javax.inject.Inject
import nl.ahmed.common.kotlin.operation.CashedFetchOperationExecutor
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.BooksRepository
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.network.api.services.BooksService
import nl.ahmed.data.storage.api.entities.BookEntity

internal class BooksRepositoryImpl @Inject constructor(
    private val booksService: BooksService,
    private val operationExecutor: CashedFetchOperationExecutor<BookDto, BookEntity, BookData>
) : BooksRepository {
    override suspend fun getBooks(keyword: String): OperationResult<List<BookData>> {
        val result = operationExecutor.execute { booksService.getAll() }
        return when(result) {
            is OperationResult.Success -> {
                val filteredBooks = result.data.filter {
                    it.author.contains(other = keyword, ignoreCase = true)
                        || it.title.contains(other = keyword, ignoreCase = true)
                }
                OperationResult.Success(filteredBooks)
            }
            is OperationResult.Failure -> result
        }
    }
}
