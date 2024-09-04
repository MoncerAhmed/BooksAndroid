package nl.ahmed.books

import androidx.navigation.NavController
import javax.inject.Inject
import nl.ahmed.core.api.di.ActivityScope
import nl.ahmed.navigation.AppNavigator

@ActivityScope
internal class AppNavigatorImpl @Inject constructor(
    private val navController: NavController
) : AppNavigator {
    override fun navigateToHome() {
        navController.navigate(R.id.home)
    }

    override fun navigateToFavorite() {
        navController.navigate(R.id.favorite)
    }

    override fun navigateToDetails(bookId: String) {
        TODO("Not yet implemented")
    }
}
