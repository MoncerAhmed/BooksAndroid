package nl.ahmed.data.storage.api.entities

import java.time.ZonedDateTime
import nl.ahmed.templates.kotlin.data.Model

interface BookEntity : Model.Entity {
    override val id: Id
    val title: String
    val createdAt: ZonedDateTime
    val author: String
    val coverUrl: String
    val description: String
    val reads: Int
    val reviews: Int
    val summary: String

    fun copyEntity(
        id: Id = this.id,
        title: String = this.title,
        createdAt: ZonedDateTime = this.createdAt,
        author: String = this.author,
        coverUrl: String = this.coverUrl,
        description: String = this.description,
        reads: Int = this.reads,
        reviews: Int = this.reviews,
        summary: String = this.summary
    ): BookEntity

    @JvmInline
    value class Id(override val value: String) : Model.Entity.Id

    interface Factory {
        fun create(
            id: Id,
            title: String,
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
