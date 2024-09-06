package nl.ahmed.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import nl.ahmed.designsystem.composables.book.BookCard
import nl.ahmed.designsystem.composables.book.models.BookCardViewState
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.templates.android.DaggerNavigatorOwnerFragment

internal class HomeFragment : DaggerNavigatorOwnerFragment<HomeNavigator>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            BooksTheme {
                Surface {
                    Greeting(
                        name = "Android",
                        modifier = Modifier
                            .statusBarsPadding()
                            .clickable {
                                navigator.navigateToFavorites()
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val items: List<BookCardViewState> = mutableListOf<BookCardViewState>().apply {
        repeat(10) {
            add(
                BookCardViewState(
                    id = it.toString(),
                    coverUrl = "https://loremflickr.com/640/480/abstract",
                    title = "Book title",
                    author = "Author name",
                    reads = "2",
                    reviews = "5",
                    isFavorite = Random.nextBoolean()
                )
            )
        }
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(items) {
            BookCard(
                bookCardViewState = it
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooksTheme {
        Greeting("Android")
    }
}
