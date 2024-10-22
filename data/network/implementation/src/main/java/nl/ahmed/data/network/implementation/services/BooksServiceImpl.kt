package nl.ahmed.data.network.implementation.services

import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.network.api.services.BooksService
import retrofit2.http.GET
import retrofit2.http.Path

internal interface BooksServiceImpl : BooksService {
    @GET("Books")
    override suspend fun getAll(): List<BookDto>

    @GET("Books/{id}")
    override suspend fun get(@Path("id") id: String): BookDto
}
