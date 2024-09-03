package nl.ahmed.data.storage.api.daos

import nl.ahmed.data.storage.api.entities.ReviewEntity

interface ReviewsDao {
    suspend fun getReviews(): List<ReviewEntity>
    suspend fun insertReviews(reviews: List<ReviewEntity>)
    suspend fun deleteAll()
}
