package nl.ahmed.navigation

interface AppNavigator {
    fun navigateToHome()

    fun navigateToFavorite()

    fun navigateToDetails(bookId: String)
}
