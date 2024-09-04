package nl.ahmed.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider
import nl.ahmed.designsystem.theme.BooksTheme
import nl.ahmed.navigation.AppNavigator

internal class HomeFragment : DaggerFragment() {
    @Inject
    lateinit var appNavigator: Provider<AppNavigator>

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
