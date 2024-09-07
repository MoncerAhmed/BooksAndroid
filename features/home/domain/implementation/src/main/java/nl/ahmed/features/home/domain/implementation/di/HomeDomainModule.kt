package nl.ahmed.features.home.domain.implementation.di

import dagger.Binds
import dagger.Module
import javax.inject.Named
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.home.domain.api.DependencyQualifiers
import nl.ahmed.features.home.domain.api.models.HomeBook
import nl.ahmed.features.home.domain.implementation.AddFavoriteUseCase
import nl.ahmed.features.home.domain.implementation.GetHomeBooksUseCase
import nl.ahmed.features.home.domain.implementation.RemoveFavoriteUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@Module
abstract class HomeDomainModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsGetHomeBooksUseCase(
        getHomeBooksUseCase: GetHomeBooksUseCase
    ): UseCase<String, OperationResult<List<HomeBook>>>

    @FragmentScope
    @Binds
    @Named(DependencyQualifiers.ADD_FAVORITE_USE_CASE)
    internal abstract fun bindsAddFavoriteUseCase(
        addFavoriteUseCase: AddFavoriteUseCase
    ): UseCase<HomeBook.Id, OperationResult<Unit>>

    @FragmentScope
    @Binds
    @Named(DependencyQualifiers.REMOVE_FAVORITE_USE_CASE)
    internal abstract fun bindsRemoveFavoriteUseCase(
        removeFavoriteUseCase: RemoveFavoriteUseCase
    ): UseCase<HomeBook.Id, OperationResult<Unit>>
}
