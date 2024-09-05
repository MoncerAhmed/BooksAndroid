package nl.ahmed.navigation

interface AppNavigator {
    fun navigateToHome(clearBackStack: Boolean= false)

    fun navigateToFavorites(clearBackStack: Boolean = false)

    fun navigateToDetails(bookId: String, clearBackStack: Boolean = false)

    fun navigateUp(): Boolean
}
