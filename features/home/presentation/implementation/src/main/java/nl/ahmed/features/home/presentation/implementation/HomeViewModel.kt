package nl.ahmed.features.home.presentation.implementation

import javax.inject.Inject
import javax.inject.Named
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.doIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.home.domain.api.DependencyQualifiers
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.features.home.presentation.implementation.mappers.BookDomainToViewStateMapper
import nl.ahmed.templates.android.MviViewModel
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class HomeViewModel @Inject constructor(
    private val getHomeBooksUseCase: UseCase<String, OperationResult<List<HomeBook>>>,
    @Named(DependencyQualifiers.ADD_FAVORITE_USE_CASE)
    private val addFavoriteUseCase: UseCase<HomeBook.Id, OperationResult<Unit>>,
    @Named(DependencyQualifiers.REMOVE_FAVORITE_USE_CASE)
    private val removeFavoriteUseCase: UseCase<HomeBook.Id, OperationResult<Unit>>,
    private val bookDomainToViewStateMapper: BookDomainToViewStateMapper
) : MviViewModel<HomeScreenState, HomeIntent, HomeSideEffect>(
    initialScreenState = HomeScreenState.Loading(searchKeyword = "")
) {

    init {
        handleIntent(HomeIntent.Initialized)
    }

    override suspend fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Initialized -> handleInitializedIntent()
            is HomeIntent.SearchKeywordChange -> handleSearchKeywordChangeIntent(intent)
            is HomeIntent.ClearSearchKeyword -> handleClearSearchKeywordIntent()
            is HomeIntent.FavoriteButtonClick -> handleFavoriteButtonClickIntent(intent)
        }
    }

    private suspend fun handleInitializedIntent() {
        loadBooks()
    }

    private suspend fun handleSearchKeywordChangeIntent(intent: HomeIntent.SearchKeywordChange) {
        updateScreenState {
            it.copyWithSearchKeyword(searchKeyword = intent.newKeyword)
        }
        loadBooks()
    }

    private suspend fun handleClearSearchKeywordIntent() {
        updateScreenState {
            it.copyWithSearchKeyword("")
        }
        loadBooks()
    }

    private suspend fun handleFavoriteButtonClickIntent(intent: HomeIntent.FavoriteButtonClick) {
        val id = HomeBook.Id(intent.bookCardViewState.id)

        suspend fun updateFavoriteItem(isFavorite: Boolean) {
            updateScreenStateIfCurrentIs<HomeScreenState.Loaded> {
                it.copy(
                    bookCardsViewStates = it.bookCardsViewStates.toMutableList().apply {
                        set(
                            indexOf(intent.bookCardViewState),
                            intent.bookCardViewState.copy(isFavorite = isFavorite)
                        )
                    }
                )
            }
        }

        val operationResult = if(intent.bookCardViewState.isFavorite) {
            updateFavoriteItem(false)
            removeFavoriteUseCase(id)
        } else {
            updateFavoriteItem(true)
            addFavoriteUseCase(id)
        }
        operationResult.doIfSuccessful { loadBooks(withLoading = false) }
    }

    private suspend fun loadBooks(withLoading: Boolean = true) {
        if(withLoading) {
            updateScreenStateIfCurrentIsNot<HomeScreenState.Loading> {
                HomeScreenState.Loading(it.searchKeyword)
            }
        }
        getHomeBooksUseCase(currentScreenState.searchKeyword)
            .doIfSuccessful { homeBooks ->
                val bookCardsViewStates = homeBooks.map { homeBook ->
                    bookDomainToViewStateMapper(homeBook)
                }
                val newScreenState = if (bookCardsViewStates.isEmpty()) {
                    HomeScreenState.Empty(searchKeyword = currentScreenState.searchKeyword)
                } else {
                    HomeScreenState.Loaded(
                        searchKeyword = currentScreenState.searchKeyword,
                        bookCardsViewStates = bookCardsViewStates
                    )
                }
                updateScreenState(newScreenState)
            }
    }
}