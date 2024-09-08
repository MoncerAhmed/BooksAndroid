package nl.ahmed.features.favorites.shared

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.favorites.ui.FavoritesNavigator
import nl.ahmed.templates.android.FeatureNavigator

@FragmentScope
internal class FavoritesNavigatorImpl @Inject constructor() : FeatureNavigator(), FavoritesNavigator {
    override fun navigateToDetails(bookId: String) {
        appNavigator.navigateToDetails(bookId = bookId)
    }
}
