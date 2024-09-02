package nl.ahmed.data.network.api.di

import nl.ahmed.data.network.api.services.BooksService
import nl.ahmed.data.network.api.services.ReviewsService

interface DataNetworkComponent {
    fun booksService(): BooksService

    fun reviewsService(): ReviewsService
}
