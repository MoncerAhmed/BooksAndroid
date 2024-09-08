package nl.ahmed.features.favorites.presentation.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.doIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.features.favorites.presentation.api.FavoritesIntent
import nl.ahmed.features.favorites.presentation.api.FavoritesScreenState
import nl.ahmed.features.favorites.presentation.api.FavoritesSideEffect
import nl.ahmed.features.favorites.presentation.implementation.mappers.BookDomainToViewStateMapper
import nl.ahmed.templates.android.MviViewModel
import nl.ahmed.templates.kotlin.domain.NoArgUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class FavoritesViewModel @Inject constructor(
    private val getFavoriteBooksUseCase: NoArgUseCase<OperationResult<List<FavoriteBook>>>,
    private val removeFavoriteUseCase: UseCase<FavoriteBook.Id, OperationResult<Unit>>,
    private val bookDomainToViewStateMapper: BookDomainToViewStateMapper
) : MviViewModel<FavoritesScreenState, FavoritesIntent, FavoritesSideEffect>(
    initialScreenState = FavoritesScreenState.Loading
) {

    init {
        handleIntent(FavoritesIntent.Initialized)
    }

    override suspend fun onIntent(intent: FavoritesIntent) {
        when (intent) {
            is FavoritesIntent.Initialized -> handleInitializedIntent()
            is FavoritesIntent.ItemClick -> handleItemClickIntent(intent)
            is FavoritesIntent.FavoriteButtonClick -> handleFavoriteButtonClickIntent(intent)
        }
    }

    private suspend fun handleInitializedIntent() {
        loadBooks()
    }

    private suspend fun handleItemClickIntent(intent: FavoritesIntent.ItemClick) {
        emitSideEffect(FavoritesSideEffect.NavigateToDetails(intent.bookCardViewState.id))
    }

    private suspend fun handleFavoriteButtonClickIntent(intent: FavoritesIntent.FavoriteButtonClick) {
        val id = FavoriteBook.Id(intent.bookCardViewState.id)
        updateScreenStateIfCurrentIs<FavoritesScreenState.Loaded> {
            it.copy(
                bookCardsViewStates = it.bookCardsViewStates.toMutableList().apply {
                    set(
                        indexOf(intent.bookCardViewState),
                        intent.bookCardViewState.copy(isFavorite = false)
                    )
                }
            )
        }
        removeFavoriteUseCase(id).doIfSuccessful { loadBooks(false) }
    }

    private suspend fun loadBooks(withLoading: Boolean = true) {
        if (withLoading) {
            updateScreenStateIfCurrentIsNot<FavoritesScreenState.Loading> {
                FavoritesScreenState.Loading
            }
        }
        getFavoriteBooksUseCase()
            .doIfSuccessful { homeBooks ->
                val bookCardsViewStates = homeBooks.map { homeBook ->
                    bookDomainToViewStateMapper(homeBook)
                }
                val newScreenState = if (bookCardsViewStates.isEmpty()) {
                    FavoritesScreenState.Empty
                } else {
                    FavoritesScreenState.Loaded(
                        bookCardsViewStates = bookCardsViewStates
                    )
                }
                updateScreenState(newScreenState)
            }
    }
}
