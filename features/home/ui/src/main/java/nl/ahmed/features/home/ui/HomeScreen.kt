package nl.ahmed.features.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.designsystem.composables.book.BookCard
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.features.home.presentation.api.HomeIntent
import nl.ahmed.features.home.presentation.api.HomeScreenState
import nl.ahmed.features.home.presentation.api.HomeSideEffect
import nl.ahmed.templates.android.BaseComposeScreen

@FragmentScope
internal class HomeScreen @Inject constructor() :
    BaseComposeScreen<HomeScreenState, HomeIntent, HomeSideEffect, HomeNavigator>() {
    @Composable
    override fun Screen(screenState: HomeScreenState, intentExecutor: (HomeIntent) -> Unit) {
        BooksTheme {
            Surface {
                HomeScreen(
                    screenState = screenState,
                    onItemClick = { intentExecutor(HomeIntent.ItemClick(it)) },
                    onSearchKeywordChange = { intentExecutor(HomeIntent.SearchKeywordChange(it)) },
                    onClearSearchKeyword = { intentExecutor(HomeIntent.ClearSearchKeyword) }
                )
            }
        }
    }

    context(PerformSideEffectScope) override suspend fun performSideEffect(sideEffect: HomeSideEffect) {
        when (sideEffect) {
            is HomeSideEffect.NavigateToDetails -> navigator.navigateToDetails(sideEffect.bookId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    screenState: HomeScreenState,
    onItemClick: (BookCardViewState) -> Unit,
    onSearchKeywordChange: (String) -> Unit,
    onClearSearchKeyword: () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = WindowInsets.safeDrawing
                            .asPaddingValues()
                            .calculateTopPadding()
                    )
            ) {
                SearchBar(
                    query = screenState.searchKeyword,
                    onQueryChange = onSearchKeywordChange,
                    onSearch = {},
                    active = false,
                    onActiveChange = {},
                    content = {},
                    trailingIcon = {
                        if (screenState.searchKeyword.isEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search icon"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Search icon",
                                modifier = Modifier.clickable(onClick = onClearSearchKeyword)
                            )
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.search_placeholder))
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(top = contentPadding.calculateTopPadding())) {
            when (screenState) {
                is HomeScreenState.Loading -> LoadingHomeScreen()
                is HomeScreenState.Empty -> EmptyHomeScreen()
                is HomeScreenState.Loaded -> LoadedHomeScreenContent(
                    screenState = screenState,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
private fun LoadingHomeScreen() {
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
private fun EmptyHomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.home_empty_label),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        )
    }
}

@Composable
private fun LoadedHomeScreenContent(
    screenState: HomeScreenState.Loaded,
    onItemClick: (BookCardViewState) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp),
    ) {
        items(screenState.bookCardsViewStates) {
            BookCard(
                bookCardViewState = it,
                onFavoriteButtonClick = null,
                modifier = Modifier.clickable { onItemClick(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeLoadedScreenPreview(
    @PreviewParameter(provider = HomeScreenPreviewParameterProvider::class)
    homeScreenState: HomeScreenState
) {
    BooksTheme {
        HomeScreen(
            screenState = homeScreenState,
            onItemClick = {},
            onSearchKeywordChange = {},
            onClearSearchKeyword = {}
        )
    }
}
