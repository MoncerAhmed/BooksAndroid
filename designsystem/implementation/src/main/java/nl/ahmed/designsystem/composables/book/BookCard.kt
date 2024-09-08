package nl.ahmed.designsystem.composables.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import nl.ahmed.designsystem.R
import nl.ahmed.designsystem.api.models.BookCardViewState
import nl.ahmed.designsystem.theme.BooksTheme

@Composable
fun BookCard(
    bookCardViewState: BookCardViewState,
    onFavoriteButtonClick: ((BookCardViewState) -> Unit)?,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Card(
            modifier = Modifier.aspectRatio(2f),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model = bookCardViewState.coverUrl),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp)
                ) {
                    BookTitleSection(
                        title = bookCardViewState.title,
                        author = bookCardViewState.author,
                        isFavorite = bookCardViewState.isFavorite,
                        onFavoriteButtonClick = onFavoriteButtonClick?.let { { onFavoriteButtonClick(bookCardViewState) } }
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    BookReadsAndReviewsSection(
                        reads = bookCardViewState.reads,
                        reviews = bookCardViewState.reviews
                    )
                }
            }
        }
    }
}

@Composable
fun BookTitleSection(
    title: String,
    author: String,
    isFavorite: Boolean,
    contentPadding: PaddingValues = PaddingValues(start = 0.dp, top = 12.dp, end = 0.dp, bottom = 0.dp),
    onFavoriteButtonClick: (() -> Unit)?
) {
    Row {
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.by),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.offset(y = (-1.7).dp)
                )
                Text(
                    text = author,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .offset(y = (-1.5).dp)
                        .padding(start = 2.dp)
                )
            }
        }
        onFavoriteButtonClick?.let {
            val favoriteIconId = if (isFavorite)
                R.drawable.ic_favorite
            else
                R.drawable.ic_not_favorite
            Image(
                painter = painterResource(id = favoriteIconId),
                contentDescription = "",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onFavoriteButtonClick()
                    }
            )
        }
    }
}

@Composable
fun BookReadsAndReviewsSection(
    reads: String,
    reviews: String
) {
    Row {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_reads),
                contentDescription = ""
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = reads,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.offset(y = (-1).dp)
                )
                Text(
                    text = stringResource(id = R.string.reads),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_reviews),
                contentDescription = ""
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = reviews,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.offset(y = (-1).dp)
                )
                Text(
                    text = stringResource(id = R.string.reviews),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
@Preview
private fun BookCardPreview(
    @PreviewParameter(provider = BookCardViewStatePreviewParameterProvider::class)
    bookCardViewState: BookCardViewState
) {
    BooksTheme {
        BookCard(
            bookCardViewState = bookCardViewState,
            onFavoriteButtonClick = { }
        )
    }
}
