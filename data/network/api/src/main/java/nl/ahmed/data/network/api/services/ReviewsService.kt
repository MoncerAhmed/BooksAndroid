package nl.ahmed.data.network.api.services

import nl.ahmed.data.network.api.dtos.ReviewDto

interface ReviewsService {
    suspend fun getReviews() : List<ReviewDto>
}
