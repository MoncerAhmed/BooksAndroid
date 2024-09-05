package nl.ahmed.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
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
    Text(
        text = "Hello Home $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooksTheme {
        Greeting("Android")
    }
}
