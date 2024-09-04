package nl.ahmed.data.dal.di

import nl.ahmed.data.dal.repositories.BooksRepository
import nl.ahmed.data.dal.repositories.FavoritesRepository

interface DataDalComponent {
    fun booksRepository(): BooksRepository
    fun favoritesRepository(): FavoritesRepository
}
