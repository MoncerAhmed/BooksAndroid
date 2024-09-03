package nl.ahmed.data.storage.implementation.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.data.storage.api.BookDatabase
import nl.ahmed.data.storage.api.daos.ReviewsDao
import nl.ahmed.data.storage.api.entities.ReviewEntity
import nl.ahmed.data.storage.implementation.entities.ReviewEntityImpl

@Module
internal class ReviewsModule {
    @Singleton
    @Provides
    fun providesReviewsDao(
        bookDatabase: BookDatabase
    ): ReviewsDao = bookDatabase.reviewsDao()

    @Singleton
    @Provides
    fun providesReviewEntityFactory(): ReviewEntity.Factory = ReviewEntityImpl.FactoryImpl()
}
