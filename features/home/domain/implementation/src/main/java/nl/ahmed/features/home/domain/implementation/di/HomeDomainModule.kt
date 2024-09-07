package nl.ahmed.features.home.domain.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.domain.implementation.GetHomeBooksUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@Module
abstract class HomeDomainModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsGetHomeBooksUseCase(
        getHomeBooksUseCase: GetHomeBooksUseCase
    ): UseCase<String, OperationResult<List<HomeBook>>>
}
