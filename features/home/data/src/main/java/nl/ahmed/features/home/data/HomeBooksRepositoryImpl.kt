package nl.ahmed.features.home.data

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.mapData
import nl.ahmed.common.kotlin.operation.mapIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.data.dal.repositories.BooksRepository
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.features.home.data.mappers.BookDalToHomeBookMapper
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.domain.api.repositories.HomeBooksRepository

@FragmentScope
internal class HomeBooksRepositoryImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val favoritesRepository: FavoritesRepository,
    private val bookDalToHomeBookMapper: BookDalToHomeBookMapper
) : HomeBooksRepository {
    override suspend fun getHomeBooks(keyword: String): OperationResult<List<HomeBook>> {
        return booksRepository.getBooks(keyword).mapIfSuccessful { bookDataList ->
            bookDataList.map { bookData ->
                val isFavorite = favoritesRepository.isFavorite(bookId = bookData.id).mapData(
                    successReducer = { it },
                    failureReducer = { false }
                )
                bookDalToHomeBookMapper.map(bookData = bookData, isFavorite = isFavorite)
            }
        }
    }
}
