package nl.ahmed.features.home.ui

import nl.ahmed.templates.kotlin.mvi.Navigator

interface HomeNavigator : Navigator {
    fun navigateToDetails(bookId: String)
}
