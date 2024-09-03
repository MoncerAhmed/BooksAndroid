package nl.ahmed.data.storage.api.entities

import java.time.ZonedDateTime

interface ReviewEntity {
    val id: Id
    val createdAt: ZonedDateTime
    val reviewerName: String
    val reviewerImgUrl: String
    val review: String

    fun copyEntity(
        id: Id,
        createdAt: ZonedDateTime,
        reviewerName: String,
        reviewerImgUrl: String,
        review: String
    ): ReviewEntity

    @JvmInline
    value class Id(val value: String)

    interface Factory {
        fun create(
            id: Id,
            createdAt: ZonedDateTime,
            reviewerName: String,
            reviewerImgUrl: String,
            review: String
        ): ReviewEntity
    }
}
