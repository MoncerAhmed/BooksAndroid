package nl.ahmed.features.favorites.domain.api.models

import java.time.ZonedDateTime

data class FavoriteBook(
    val id: Id,
    val title: String,
    val author: String,
    val coverUrl: String,
    val createdAt: ZonedDateTime,
    val reads: Int,
    val reviews: Int
) {
    @JvmInline
    value class Id(val value: String)
}
