package nl.ahmed.data.network.implementation.services

import nl.ahmed.data.network.api.dtos.ReviewDto
import nl.ahmed.data.network.api.services.ReviewsService
import retrofit2.http.GET

internal interface ReviewsServiceImpl : ReviewsService {
    @GET("reviews")
    override suspend fun getReviews(): List<ReviewDto>
}
