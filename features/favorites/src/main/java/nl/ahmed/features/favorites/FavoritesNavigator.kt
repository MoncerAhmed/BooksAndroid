package nl.ahmed.features.favorites

import javax.inject.Inject
import nl.ahmed.templates.android.FeatureNavigator

internal class FavoritesNavigator @Inject constructor() : FeatureNavigator() {
    fun navigateToHome() {
        appNavigator.navigateToHome()
    }
}
