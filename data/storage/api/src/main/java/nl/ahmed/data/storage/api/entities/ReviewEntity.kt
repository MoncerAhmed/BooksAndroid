package nl.ahmed.data.storage.api.entities

import java.time.ZonedDateTime
import nl.ahmed.common.kotlin.templates.Model

interface ReviewEntity : Model.Entity {
    override val id: Id
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
    value class Id(override val value: String) : Model.Entity.Id

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
