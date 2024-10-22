package nl.ahmed.data.network.api.dtos

import java.time.ZonedDateTime
import nl.ahmed.templates.kotlin.data.Model

data class BookDto(
    val id: Id,
    val createdAt: ZonedDateTime,
    val title: String,
    val author: String,
    val coverUrl: String,
    val description: String,
    val reads: Int,
    val reviews: Int,
    val summary: String
) : Model.Dto {
    @JvmInline
    value class Id(val value: String)
}
