package nl.ahmed.features.details.data

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.mapData
import nl.ahmed.common.kotlin.operation.mapIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.models.BookData
import nl.ahmed.data.dal.repositories.BooksRepository
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.features.details.data.mappers.BookDalToBookDetailsMapper
import nl.ahmed.features.details.domain.api.models.BookDetails
import nl.ahmed.features.details.domain.api.repositories.BookDetailsRepository

@FragmentScope
internal class BookDetailsRepositoryImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val favoritesRepository: FavoritesRepository,
    private val bookDalToBookDetailsMapper: BookDalToBookDetailsMapper
) : BookDetailsRepository {
    override suspend fun getBookDetails(bookId: BookDetails.Id): OperationResult<BookDetails> {
        return booksRepository.getBook(bookId = BookData.Id(bookId.value)).mapIfSuccessful { bookData ->
            val isFavorite = favoritesRepository.isFavorite(bookId = bookData.id).mapData(
                successReducer = { it },
                failureReducer = { false }
            )
            bookDalToBookDetailsMapper(bookData, isFavorite)
        }
    }
}
