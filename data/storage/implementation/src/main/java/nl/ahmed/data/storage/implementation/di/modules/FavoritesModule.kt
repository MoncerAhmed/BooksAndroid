package nl.ahmed.data.storage.implementation.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import nl.ahmed.data.storage.api.BookDatabase
import nl.ahmed.data.storage.api.daos.FavoritesDao
import nl.ahmed.data.storage.api.entities.FavoriteEntity
import nl.ahmed.data.storage.implementation.entities.FavoriteEntityImpl

@Module
internal class FavoritesModule {
    @Singleton
    @Provides
    fun providesFavoritesDao(
        bookDatabase: BookDatabase
    ): FavoritesDao = bookDatabase.favoritesDao()

    @Singleton
    @Provides
    fun providesFavoriteEntityFactory(): FavoriteEntity.Factory = FavoriteEntityImpl.FactoryImpl()
}
