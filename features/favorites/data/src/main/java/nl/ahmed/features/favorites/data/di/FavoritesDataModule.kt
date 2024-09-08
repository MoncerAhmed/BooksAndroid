package nl.ahmed.features.favorites.data.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.favorites.data.FavoriteBooksRepositoryImpl
import nl.ahmed.features.favorites.domain.api.repositories.FavoriteBooksRepository

@Module
abstract class FavoritesDataModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsHomeBooksRepository(
        homeBooksRepositoryImpl: FavoriteBooksRepositoryImpl
    ): FavoriteBooksRepository
}
