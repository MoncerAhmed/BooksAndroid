package nl.ahmed.features.details.domain.api.models

import java.time.ZonedDateTime

data class BookDetails(
    val id: Id,
    val title: String,
    val author: String,
    val coverUrl: String,
    val createdAt: ZonedDateTime,
    val description: String,
    val summary: String,
    val reads: Int,
    val reviews: Int,
    val isFavorite: Boolean
) {
    @JvmInline
    value class Id(val value: String)
}
