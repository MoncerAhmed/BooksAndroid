package nl.ahmed.features.favorites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.designsystem.composables.book.BookCard
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.features.favorites.presentation.api.FavoritesIntent
import nl.ahmed.features.favorites.presentation.api.FavoritesScreenState
import nl.ahmed.features.favorites.presentation.api.FavoritesSideEffect
import nl.ahmed.templates.android.BaseComposeScreen

@FragmentScope
internal class FavoritesScreen @Inject constructor() : BaseComposeScreen<FavoritesScreenState, FavoritesIntent, FavoritesSideEffect, FavoritesNavigator>() {
    @Composable
    override fun Screen(screenState: FavoritesScreenState, intentExecutor: (FavoritesIntent) -> Unit) {
        BooksTheme {
            Surface {
                FavoritesScreen(
                    screenState = screenState,
                    onItemClick = { intentExecutor(FavoritesIntent.ItemClick(it)) },
                    onFavoriteButtonClick = { intentExecutor(FavoritesIntent.FavoriteButtonClick(it)) }
                )
            }
        }
    }

    context(PerformSideEffectScope) override suspend fun performSideEffect(sideEffect: FavoritesSideEffect) {
        when(sideEffect) {
            is FavoritesSideEffect.NavigateToDetails -> navigator.navigateToHome() // TODO: Navigate to details
        }
    }
}

@Composable
private fun FavoritesScreen(
    screenState: FavoritesScreenState,
    onItemClick: (BookCardViewState) -> Unit,
    onFavoriteButtonClick: (BookCardViewState) -> Unit
) {
    Surface(modifier = Modifier.statusBarsPadding()) {
        when(screenState) {
            is FavoritesScreenState.Loading -> LoadingFavoritesScreen()
            is FavoritesScreenState.Empty -> EmptyFavoritesScreen()
            is FavoritesScreenState.Loaded -> LoadedFavoritesScreenContent(
                screenState = screenState,
                onItemClick = onItemClick,
                onFavoriteButtonClick = onFavoriteButtonClick
            )
        }
    }
}

@Composable
private fun LoadingFavoritesScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(32.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        )
    }
}

@Composable
private fun EmptyFavoritesScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.favorite_empty_label),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
                .padding(horizontal = 32.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LoadedFavoritesScreenContent(
    screenState: FavoritesScreenState.Loaded,
    onItemClick: (BookCardViewState) -> Unit,
    onFavoriteButtonClick: (BookCardViewState) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp),
    ) {
        items(screenState.bookCardsViewStates) {
            BookCard(
                bookCardViewState = it,
                modifier = Modifier.clickable { onItemClick(it) },
                onFavoriteButtonClick = onFavoriteButtonClick
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
