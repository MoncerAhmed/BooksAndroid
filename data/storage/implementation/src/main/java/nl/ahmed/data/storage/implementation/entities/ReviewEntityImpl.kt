package nl.ahmed.data.storage.implementation.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime
import nl.ahmed.data.storage.api.entities.ReviewEntity

@Entity(tableName = "reviews")
internal data class ReviewEntityImpl(
    @PrimaryKey override val id: ReviewEntity.Id,
    override val createdAt: ZonedDateTime,
    override val reviewerName: String,
    override val reviewerImgUrl: String,
    override val review: String
) : ReviewEntity {
    override fun copyEntity(
        id: ReviewEntity.Id,
        createdAt: ZonedDateTime,
        reviewerName: String,
        reviewerImgUrl: String,
        review: String
    ): ReviewEntity = copy(
        id = id,
        createdAt = createdAt,
        reviewerName = reviewerName,
        reviewerImgUrl = reviewerImgUrl,
        review = review
    )

    class FactoryImpl : ReviewEntity.Factory {
        override fun create(
            id: ReviewEntity.Id,
            createdAt: ZonedDateTime,
            reviewerName: String,
            reviewerImgUrl: String,
            review: String
        ): ReviewEntity = ReviewEntityImpl(
            id = id,
            createdAt = createdAt,
            reviewerName = reviewerName,
            reviewerImgUrl = reviewerImgUrl,
            review = review
        )
    }
}
