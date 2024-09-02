package nl.ahmed.data.network.api.services

import nl.ahmed.data.network.api.dtos.BookDto

interface BooksService {
    suspend fun getBooks(): List<BookDto>
    suspend fun getBook(id: BookDto.Id): BookDto
}
