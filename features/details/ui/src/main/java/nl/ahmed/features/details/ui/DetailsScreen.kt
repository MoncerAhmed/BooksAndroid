package nl.ahmed.features.details.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.designsystem.composables.book.BookReadsAndReviewsSection
import nl.ahmed.designsystem.composables.book.BookTitleSection
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.features.details.presentation.api.DetailsIntent
import nl.ahmed.features.details.presentation.api.DetailsScreenState
import nl.ahmed.features.details.presentation.api.DetailsSideEffect
import nl.ahmed.templates.android.BaseComposeScreen

@FragmentScope
internal class DetailsScreen @Inject constructor() :
    BaseComposeScreen<DetailsScreenState, DetailsIntent, DetailsSideEffect, DetailsNavigator>() {
    @Composable
    override fun Screen(screenState: DetailsScreenState, intentExecutor: (DetailsIntent) -> Unit) {
        BooksTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                DetailsScreen(
                    screenState = screenState,
                    onFavoriteButtonClick = { intentExecutor(DetailsIntent.FavoriteButtonClick) },
                    onBackButtonClick = { intentExecutor(DetailsIntent.BackButtonClick) }
                )
            }
        }
    }

    context(PerformSideEffectScope) override suspend fun performSideEffect(sideEffect: DetailsSideEffect) {
        when (sideEffect) {
            is DetailsSideEffect.NavigateBack -> navigator.navigateUp()
        }
    }
}

@Composable
private fun DetailsScreen(
    screenState: DetailsScreenState,
    onFavoriteButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        when (screenState) {
            is DetailsScreenState.Loading -> LoadingDetailsScreen()
            is DetailsScreenState.Loaded -> LoadedDetailsScreenContent(
                screenState = screenState,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onBackButtonClick = onBackButtonClick
            )
        }
    }
}

@Composable
private fun LoadingDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateTopPadding()
            )
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(32.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun LoadedDetailsScreenContent(
    screenState: DetailsScreenState.Loaded,
    onFavoriteButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.navigationBars,
        topBar = {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.surface,
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                                    Color.Transparent
                                )
                            )
                        )
                ) {
                    Card(
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .padding(
                                top = WindowInsets.safeDrawing
                                    .asPaddingValues()
                                    .calculateTopPadding(),
                                start = 16.dp
                            )
                            .zIndex(2f)
                            .size(48.dp)
                            .clickable(onClick = onBackButtonClick)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val coverHeight = this.maxWidth * 0.8f
            Image(
                painter = rememberAsyncImagePainter(screenState.coverUrl),
                modifier = Modifier
                    .width(this.maxWidth)
                    .height(coverHeight)
                    .background(Color.Gray),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .heightIn(min = this@BoxWithConstraints.maxHeight)
                        .padding(top = coverHeight - 20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .padding(bottom = 32.dp)
                    ) {
                        BookTitleSection(
                            title = screenState.title,
                            author = screenState.author,
                            isFavorite = screenState.isFavorite,
                            contentPadding = PaddingValues(top = 24.dp),
                            onFavoriteButtonClick = onFavoriteButtonClick
                        )
                        BookReadsAndReviewsSection(
                            reads = screenState.reads,
                            reviews = screenState.reviews
                        )
                        DetailsSection(
                            description = screenState.description,
                            summary = screenState.summary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsSection(
    description: String,
    summary: String
) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(text = stringResource(id = R.string.details_description_label), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = description, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.details_summary_label), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = summary, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsLoadedScreenPreview() {
    BooksTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoadedDetailsScreenContent(
                screenState = DetailsScreenState.Loaded(
                    createdAt = "8th September 2024",
                    title = "Title",
                    author = "Author",
                    coverUrl = "",
                    description = "Qui amet veritatis ex. Voluptatibus quibusdam consectetur voluptates. Qui excepturi nulla quia esse saepe quidem veniam nihil. Sit ipsum commodi debitis totam rem sequi iure.",
                    summary = "Ex optio neque eaque odio id velit dolorem deserunt quidem. Animi a placeat itaque voluptatum beatae aspernatur. Velit nobis eligendi nesciunt.\n\nQuos repudiandae voluptatibus deleniti exercitationem at consequatur. Odio recusandae nemo delectus hic illo quaerat explicabo dolore illo. Aliquid maiores illo eveniet possimus magnam assumenda ratione aspernatur vero.\n\nRem tempora eveniet at vero. Provident dignissimos incidunt. Consequatur maxime voluptatibus blanditiis eum iure. Illum rem eveniet expedita.",
                    reads = "2",
                    reviews = "2",
                    isFavorite = false
                ),
                onFavoriteButtonClick = {},
                onBackButtonClick = {}
            )
        }
    }
}
