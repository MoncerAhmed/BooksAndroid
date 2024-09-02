package nl.ahmed.data.network.api.dtos

import java.time.LocalDateTime

data class ReviewDto(
    val id: Id,
    val createdAt: LocalDateTime,
    val readerName: String,
    val readerImgUrl: String,
    val review: String
) {
    @JvmInline
    value class Id(val value: String)
}
