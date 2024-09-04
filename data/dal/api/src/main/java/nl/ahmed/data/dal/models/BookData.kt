package nl.ahmed.data.dal.models

import java.time.ZonedDateTime
import nl.ahmed.common.kotlin.templates.Model

data class BookData(
    val id: Id,
    val title: String,
    val createdAt: ZonedDateTime,
    val author: String,
    val coverUrl: String,
    val description: String,
    val reads: Int,
    val reviews: Int,
    val summary: String
): Model.Data {
    @JvmInline
    value class Id(val value: String)
}
