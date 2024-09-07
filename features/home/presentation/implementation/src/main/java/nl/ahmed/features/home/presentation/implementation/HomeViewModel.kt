package nl.ahmed.features.home.presentation.implementation

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.doIfSuccessful
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.templates.android.MviViewModel
import nl.ahmed.templates.kotlin.domain.UseCase

@FragmentScope
internal class HomeViewModel @Inject constructor(
    private val getHomeBooksUseCase: UseCase<String, OperationResult<List<HomeBook>>>
) : MviViewModel<HomeScreenState, HomeIntent, HomeSideEffect>(
    initialScreenState = HomeScreenState.Loading
){

    init {
        handleIntent(HomeIntent.Initialized)
    }

    override suspend fun onIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.Initialized -> handleInitializedIntent()
        }
    }

    private suspend fun handleInitializedIntent() {
        getHomeBooksUseCase("")
            .doIfSuccessful { homeBooks ->
                val bookCardsViewStates = homeBooks.map { homeBook ->
                    BookCardViewState(
                        id = homeBook.id.value,
                        coverUrl = homeBook.coverUrl,
                        title = homeBook.title,
                        author = homeBook.author,
                        reads = homeBook.reads.toString(),
                        reviews = homeBook.reviews.toString(),
                        isFavorite = homeBook.isFavorite
                    )
                }
                updateScreenState {
                    HomeScreenState.Loaded(
                        bookCardsViewStates = bookCardsViewStates
                    )
                }
            }
    }
}
