package nl.ahmed.data.network.api.dtos

import java.time.LocalDateTime

data class BookDto(
    val id: Id,
    val createdAt: LocalDateTime,
    val author: String,
    val coverUrl: String,
    val description: String,
    val read: Int,
    val reviews: Int,
    val summary: String
) {
    @JvmInline
    value class Id(val value: String)
}
