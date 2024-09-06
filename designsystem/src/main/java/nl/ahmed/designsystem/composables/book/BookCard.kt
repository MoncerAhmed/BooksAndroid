package nl.ahmed.designsystem.composables.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import nl.ahmed.designsystem.R
import nl.ahmed.designsystem.composables.book.models.BookCardViewState
import nl.ahmed.designsystem.composables.book.models.BookCardViewStatePreviewParameterProvider
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.designsystem.utils.rememberAsyncImageNonCacheablePainter

@Composable
fun BookCard(bookCardViewState: BookCardViewState, modifier: Modifier = Modifier) {
    Column(modifier) {
        Card(
            modifier = Modifier.aspectRatio(2f),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {
            Box {
                Image(
                    painter = rememberAsyncImageNonCacheablePainter(model = bookCardViewState.coverUrl),
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
                    Row {
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = bookCardViewState.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.by),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.offset(y = (-1.7).dp)
                            )
                            Text(
                                text = bookCardViewState.author,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .offset(y = (-1.5).dp)
                                    .padding(start = 2.dp)
                            )
                        }
                        val favoriteIconId = if (bookCardViewState.isFavorite)
                            R.drawable.ic_favorite
                        else
                            R.drawable.ic_not_favorite
                        Image(
                            painter = painterResource(id = favoriteIconId),
                            contentDescription = "",
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                    Row(modifier = Modifier.padding(top = 8.dp)) {
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
                                    text = bookCardViewState.reads,
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
                                    text = bookCardViewState.reviews,
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
        BookCard(bookCardViewState = bookCardViewState)
    }
}
