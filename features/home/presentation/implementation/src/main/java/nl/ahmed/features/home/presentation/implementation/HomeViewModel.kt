package nl.ahmed.features.home.presentation.implementation

import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.doIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.features.home.presentation.implementation.mappers.BookDomainToViewStateMapper
import nl.ahmed.templates.android.MviViewModel
import nl.ahmed.templates.kotlin.domain.UseCase

@OptIn(FlowPreview::class)
@FragmentScope
internal class HomeViewModel @Inject constructor(
    private val getHomeBooksUseCase: UseCase<String, OperationResult<List<HomeBook>>>,
    private val bookDomainToViewStateMapper: BookDomainToViewStateMapper
) : MviViewModel<HomeScreenState, HomeIntent, HomeSideEffect>(
    initialScreenState = HomeScreenState.Loading(searchKeyword = "")
) {

    private val searchKeywordFlow = MutableStateFlow(currentScreenState.searchKeyword)

    init {
        viewModelScope.launch {
            searchKeywordFlow
                .debounce(300L)
                .distinctUntilChanged()
                .collect {
                    loadBooks()
                }
        }
    }

    override suspend fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Initialized -> handleInitializedIntent()
            is HomeIntent.ItemClick -> handleItemClickIntent(intent)
            is HomeIntent.SearchKeywordChange -> handleSearchKeywordChangeIntent(intent)
            is HomeIntent.ClearSearchKeyword -> handleClearSearchKeywordIntent()
        }
    }

    private suspend fun handleInitializedIntent() {
        loadBooks()
    }

    private suspend fun handleItemClickIntent(intent: HomeIntent.ItemClick) {
        emitSideEffect(HomeSideEffect.NavigateToDetails(intent.bookCardViewState.id))
    }

    private suspend fun handleSearchKeywordChangeIntent(intent: HomeIntent.SearchKeywordChange) {
        updateScreenState {
            it.copyWithSearchKeyword(searchKeyword = intent.newKeyword)
        }
        searchKeywordFlow.emit(intent.newKeyword)
    }

    private suspend fun handleClearSearchKeywordIntent() {
        updateScreenState {
            it.copyWithSearchKeyword("")
        }
        searchKeywordFlow.emit("")
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
