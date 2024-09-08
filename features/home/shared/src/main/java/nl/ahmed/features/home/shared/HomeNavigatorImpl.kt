package nl.ahmed.features.home.shared

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.home.ui.HomeNavigator
import nl.ahmed.templates.android.FeatureNavigator

@FragmentScope
internal class HomeNavigatorImpl @Inject constructor() : FeatureNavigator(), HomeNavigator {
    override fun navigateToDetails(bookId: String) {
        appNavigator.navigateToDetails(bookId)
    }
}
