package nl.ahmed.feature.home

import javax.inject.Inject
import nl.ahmed.templates.android.FeatureNavigator

internal class HomeNavigator @Inject constructor() : FeatureNavigator() {
    fun navigateToFavorites() {
        appNavigator.navigateToFavorites()
    }
}
