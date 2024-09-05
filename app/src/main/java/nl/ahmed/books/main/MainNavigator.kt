package nl.ahmed.books.main

import javax.inject.Inject
import nl.ahmed.templates.android.FeatureNavigator

internal class MainNavigator @Inject constructor() : FeatureNavigator() {

    fun navigateToHome() {
        appNavigator.navigateToHome(clearBackStack = true)
    }

    fun navigateToFavorite() {
        appNavigator.navigateToFavorites(clearBackStack = true)
    }
}
