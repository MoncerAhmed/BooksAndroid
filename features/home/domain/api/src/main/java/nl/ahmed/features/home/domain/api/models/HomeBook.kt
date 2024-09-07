package nl.ahmed.features.home.domain.api.models

import java.time.ZonedDateTime

data class HomeBook(
    val id: Id,
    val title: String,
    val author: String,
    val coverUrl: String,
    val createdAt: ZonedDateTime,
    val reads: Int,
    val reviews: Int,
    val isFavorite: Boolean
) {
    @JvmInline
    value class Id(val value: String)
}
