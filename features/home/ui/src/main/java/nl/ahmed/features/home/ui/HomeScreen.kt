package nl.ahmed.features.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import javax.inject.Inject
import kotlin.random.Random
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.designsystem.composables.book.BookCard
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.templates.android.BaseComposeScreen

@FragmentScope
internal class HomeScreen @Inject constructor() : BaseComposeScreen<HomeScreenState, HomeIntent, HomeSideEffect, HomeNavigator>() {
    @Composable
    override fun Screen(screenState: HomeScreenState, intentExecutor: (HomeIntent) -> Unit) {
        BooksTheme {
            Surface {
                HomeScreen(screenState) {
                    navigator.navigateToFavorites()
                }
            }
        }
    }

    context(PerformSideEffectScope) override suspend fun performSideEffect(sideEffect: HomeSideEffect) {
        TODO("Not yet implemented")
    }
}

@Composable
private fun HomeScreen(
    screenState: HomeScreenState,
    onClick: () -> Unit
) {
    when(screenState) {
        is HomeScreenState.Loading -> LoadingHomeScreen()
        is HomeScreenState.Loaded -> LoadedHomeScreenContent(screenState)
    }
}

@Composable
private fun LoadingHomeScreen() {
    CircularProgressIndicator()
}

@Composable
private fun LoadedHomeScreenContent(screenState: HomeScreenState.Loaded) {
    LazyColumn(
        modifier = Modifier
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(screenState.bookCardsViewStates) {
            BookCard(
                bookCardViewState = it
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    BooksTheme {
        // Greeting("Android")
    }
}
