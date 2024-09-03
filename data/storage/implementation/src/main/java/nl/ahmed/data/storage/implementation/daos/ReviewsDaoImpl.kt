package nl.ahmed.data.storage.implementation.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import nl.ahmed.data.storage.api.daos.ReviewsDao
import nl.ahmed.data.storage.api.entities.ReviewEntity
import nl.ahmed.data.storage.implementation.entities.ReviewEntityImpl

@Dao
internal interface ReviewsDaoImpl : ReviewsDao {
    override suspend fun getReviews(): List<ReviewEntity> {
        return _getReviews()
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun insertReviews(reviews: List<ReviewEntity>) {
        _insertReviews(reviews = reviews as List<ReviewEntityImpl>)
    }

    override suspend fun deleteAll() {
        _deleteAll()
    }

    @Query("SELECT * FROM reviews")
    suspend fun _getReviews(): List<ReviewEntityImpl>

    @Insert
    suspend fun _insertReviews(reviews: List<ReviewEntityImpl>)

    @Query("DELETE FROM reviews")
    suspend fun _deleteAll()
}
