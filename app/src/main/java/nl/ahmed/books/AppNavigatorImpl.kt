package nl.ahmed.books

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import javax.inject.Inject
import nl.ahmed.common.kotlin.di.ActivityScope
import nl.ahmed.navigation.AppNavigator

@ActivityScope
internal class AppNavigatorImpl @Inject constructor(
    private val navHostFragment: NavHostFragment,
    private val navController: NavController
) : AppNavigator {
    override fun navigateToHome(clearBackStack: Boolean) {
        navController.navigate(
            directions = NavGraphDirections.actionHome(),
            navOptions = getNavOptions(clearBackStack)
        )
    }

    override fun navigateToFavorites(clearBackStack: Boolean) {
        navController.navigate(
            directions = NavGraphDirections.actionFavorites(),
            navOptions = getNavOptions(clearBackStack)
        )
    }

    override fun navigateToDetails(bookId: String, clearBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun navigateUp(): Boolean {
        if (navHostFragment.childFragmentManager.backStackEntryCount > 0) {
            navController.navigateUp()
            return true
        }
        return false
    }

    private fun getNavOptions(clearBackStack: Boolean): NavOptions {
        val navOptionsBuilder = NavOptions.Builder()
        if (clearBackStack) {
            navOptionsBuilder.setPopUpTo(R.id.nav_graph, inclusive = true)
        }
        return navOptionsBuilder.build()
    }
}
