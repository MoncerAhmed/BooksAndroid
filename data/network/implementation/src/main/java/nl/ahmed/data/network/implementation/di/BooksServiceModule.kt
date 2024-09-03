package nl.ahmed.data.network.implementation.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.data.network.api.services.BooksService
import nl.ahmed.data.network.implementation.services.BooksServiceImpl
import retrofit2.Retrofit

@Module
internal class BooksServiceModule {
    @Singleton
    @Provides
    fun providesBooksService(
        retrofit: Retrofit
    ): BooksService = retrofit.create(BooksServiceImpl::class.java)
}
