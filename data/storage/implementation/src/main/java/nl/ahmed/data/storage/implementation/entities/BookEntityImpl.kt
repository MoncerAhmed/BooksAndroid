package nl.ahmed.data.storage.implementation.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime
import nl.ahmed.data.storage.api.entities.BookEntity

@Entity(tableName = "books")
internal data class BookEntityImpl(
    @PrimaryKey override val id: BookEntity.Id,
    override val createdAt: ZonedDateTime,
    override val author: String,
    override val coverUrl: String,
    override val description: String,
    override val reads: Int,
    override val reviews: Int,
    override val summary: String
) : BookEntity {

    override fun copyEntity(
        id: BookEntity.Id,
        createdAt: ZonedDateTime,
        author: String,
        coverUrl: String,
        description: String,
        reads: Int,
        reviews: Int,
        summary: String
    ): BookEntity = copy(
        id = id,
        createdAt = createdAt,
        author = author,
        coverUrl = coverUrl,
        description = description,
        reads = reads,
        reviews = reviews,
        summary = summary
    )

    class FactoryImpl : BookEntity.Factory {
        override fun create(
            id: BookEntity.Id,
            createdAt: ZonedDateTime,
            author: String,
            coverUrl: String,
            description: String,
            reads: Int,
            reviews: Int,
            summary: String
        ): BookEntity = BookEntityImpl(
            id = id,
            createdAt = createdAt,
            author = author,
            coverUrl = coverUrl,
            description = description,
            reads = reads,
            reviews = reviews,
            summary = summary
        )
    }
}
