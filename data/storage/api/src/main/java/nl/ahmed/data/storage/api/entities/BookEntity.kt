package nl.ahmed.data.storage.api.entities

import java.time.ZonedDateTime

interface BookEntity {
    val id: Id
    val createdAt: ZonedDateTime
    val author: String
    val coverUrl: String
    val description: String
    val reads: Int
    val reviews: Int
    val summary: String

    fun copyEntity(
        id: Id = this.id,
        createdAt: ZonedDateTime = this.createdAt,
        author: String = this.author,
        coverUrl: String = this.coverUrl,
        description: String = this.description,
        reads: Int = this.reads,
        reviews: Int = this.reviews,
        summary: String = this.summary
    ): BookEntity

    @JvmInline
    value class Id(val value: String)

    interface Factory {
        fun create(
            id: Id,
            createdAt: ZonedDateTime,
            author: String,
            coverUrl: String,
            description: String,
            reads: Int,
            reviews: Int,
            summary: String
        ): BookEntity
    }
}
