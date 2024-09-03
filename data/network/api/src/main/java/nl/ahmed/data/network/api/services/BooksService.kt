package nl.ahmed.data.network.api.services

import nl.ahmed.data.network.api.dtos.BookDto

interface BooksService {
    suspend fun getAll(): List<BookDto>

    suspend fun get(id: String): BookDto
}
