package nl.ahmed.data.dal

import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData

interface BooksRepository {
    suspend fun getBooks(keyword: String = ""): OperationResult<List<BookData>>
}