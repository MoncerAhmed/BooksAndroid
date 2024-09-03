package nl.ahmed.data.network.api.di

import nl.ahmed.data.network.api.services.BooksService

interface DataNetworkComponent {
    fun booksService(): BooksService
}
