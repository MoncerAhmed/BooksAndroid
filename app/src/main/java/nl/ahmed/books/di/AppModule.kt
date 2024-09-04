package nl.ahmed.books.di

import dagger.Module
import dagger.Provides
import nl.ahmed.core.api.di.AppScope

@Module
internal class AppModule {
    @AppScope
    @Provides
    fun provideString() = "Hello"
}
