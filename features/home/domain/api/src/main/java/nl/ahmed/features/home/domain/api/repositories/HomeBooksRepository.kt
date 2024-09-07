package nl.ahmed.features.home.domain.api.repositories

import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.home.domain.api.models.HomeBook

interface HomeBooksRepository {
    suspend fun getHomeBooks(keyword: String): OperationResult<List<HomeBook>>
}
