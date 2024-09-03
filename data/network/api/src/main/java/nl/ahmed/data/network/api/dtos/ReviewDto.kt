package nl.ahmed.data.network.api.dtos

import java.time.ZonedDateTime

data class ReviewDto(
    val id: Id,
    val createdAt: ZonedDateTime,
    val reviewerName: String,
    val reviewerImgUrl: String,
    val review: String
) {
    @JvmInline
    value class Id(val value: String)
}
