package nl.ahmed.data.network.implementation.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.data.network.api.services.BooksService
import nl.ahmed.data.network.api.services.ReviewsService
import nl.ahmed.data.network.implementation.services.BooksServiceImpl
import nl.ahmed.data.network.implementation.services.ReviewsServiceImpl
import retrofit2.Retrofit

@Module
internal class DataNetworkModule {
    @Singleton
    @Provides
    fun providesBooksService(
        retrofit: Retrofit
    ): BooksService = retrofit.create(BooksServiceImpl::class.java)

    @Singleton
    @Provides
    fun providesReviewsService(
        retrofit: Retrofit
    ): ReviewsService = retrofit.create(ReviewsServiceImpl::class.java)
}
