package nl.ahmed.data.dal.di

import nl.ahmed.data.dal.BooksRepository

interface DataDalComponent {
    fun booksRepository(): BooksRepository
}
