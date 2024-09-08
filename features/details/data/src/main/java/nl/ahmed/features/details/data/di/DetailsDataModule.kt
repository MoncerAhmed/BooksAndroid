package nl.ahmed.features.details.data.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.details.data.BookDetailsRepositoryImpl
import nl.ahmed.features.details.domain.api.repositories.BookDetailsRepository

@Module
abstract class DetailsDataModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsHomeBooksRepository(
        homeBooksRepositoryImpl: BookDetailsRepositoryImpl
    ): BookDetailsRepository
}
