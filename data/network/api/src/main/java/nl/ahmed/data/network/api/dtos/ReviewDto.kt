package nl.ahmed.data.network.api.dtos

import java.time.ZonedDateTime
import nl.ahmed.templates.kotlin.Model

data class ReviewDto(
    val id: Id,
    val createdAt: ZonedDateTime,
    val reviewerName: String,
    val reviewerImgUrl: String,
    val review: String
) : Model.Dto {
    @JvmInline
    value class Id(val value: String)
}
