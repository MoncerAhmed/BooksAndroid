package nl.ahmed.books.di.app

import dagger.Module
import dagger.Provides
import nl.ahmed.common.kotlin.di.AppScope

@Module
internal class AppModule {
    @AppScope
    @Provides
    fun provideString() = "Hello"
}
