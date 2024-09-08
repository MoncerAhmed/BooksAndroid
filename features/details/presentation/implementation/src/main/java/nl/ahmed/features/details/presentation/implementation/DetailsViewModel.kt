package nl.ahmed.features.details.presentation.implementation

import javax.inject.Inject
import javax.inject.Named
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.doIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.details.domain.api.DependencyQualifiers
import nl.ahmed.features.details.domain.api.models.BookDetails
import nl.ahmed.features.details.presentation.api.DetailsIntent
import nl.ahmed.features.details.presentation.api.DetailsScreenState
import nl.ahmed.features.details.presentation.api.DetailsSideEffect
import nl.ahmed.templates.android.MviViewModel
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class DetailsViewModel @Inject constructor(
    private val getBookDetailsUseCaseDetails: UseCase<BookDetails.Id, OperationResult<BookDetails>>,
    @Named(DependencyQualifiers.REMOVE_FAVORITE_USE_CASE)
    private val removeFavoriteUseCase: UseCase<BookDetails.Id, OperationResult<Unit>>,
    @Named(DependencyQualifiers.ADD_FAVORITE_USE_CASE)
    private val addFavoriteUseCase: UseCase<BookDetails.Id, OperationResult<Unit>>
) : MviViewModel<DetailsScreenState, DetailsIntent, DetailsSideEffect>(
    initialScreenState = DetailsScreenState.Loading
) {

    private var bookId: BookDetails.Id = BookDetails.Id("-1")

    override suspend fun onIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.Initialized -> handleInitializedIntent(intent)
            is DetailsIntent.FavoriteButtonClick -> handleFavoriteButtonClickIntent()
            is DetailsIntent.BackButtonClick -> handleBackButtonClickIntent()
        }
    }

    private suspend fun handleInitializedIntent(intent: DetailsIntent.Initialized) {
        bookId = BookDetails.Id(intent.bookId)
        loadBookDetails()
    }

    private suspend fun handleFavoriteButtonClickIntent() {
        doIfCurrentScreenStateIs<DetailsScreenState.Loaded> { loadedScreenState ->
            updateScreenState(loadedScreenState.copy(isFavorite = !loadedScreenState.isFavorite))

            val operationResult = if (loadedScreenState.isFavorite) {
                removeFavoriteUseCase(bookId)
            } else {
                addFavoriteUseCase(bookId)
            }
            operationResult.doIfSuccessful { loadBookDetails(false) }
        }
    }

    private suspend fun handleBackButtonClickIntent() {
        emitSideEffect(DetailsSideEffect.NavigateBack)
    }

    private suspend fun loadBookDetails(withLoading: Boolean = true) {
        if (withLoading) {
            updateScreenStateIfCurrentIsNot<DetailsScreenState.Loading> {
                DetailsScreenState.Loading
            }
        }
        getBookDetailsUseCaseDetails(bookId)
            .doIfSuccessful { bookDetails ->
                updateScreenState(
                    DetailsScreenState.Loaded(
                        createdAt = bookDetails.createdAt.toString(),
                        title = bookDetails.title,
                        author = bookDetails.author,
                        coverUrl = bookDetails.coverUrl,
                        description = bookDetails.description,
                        summary = bookDetails.summary,
                        reads = bookDetails.reads.toString(),
                        reviews = bookDetails.reviews.toString(),
                        isFavorite = bookDetails.isFavorite
                    )
                )
            }
    }
}
