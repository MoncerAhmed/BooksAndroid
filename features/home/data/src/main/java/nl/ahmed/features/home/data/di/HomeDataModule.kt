package nl.ahmed.features.home.data.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.home.data.HomeBooksRepositoryImpl
import nl.ahmed.features.home.domain.api.repositories.HomeBooksRepository

@Module
abstract class HomeDataModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsHomeBooksRepository(
        homeBooksRepositoryImpl: HomeBooksRepositoryImpl
    ): HomeBooksRepository
}
