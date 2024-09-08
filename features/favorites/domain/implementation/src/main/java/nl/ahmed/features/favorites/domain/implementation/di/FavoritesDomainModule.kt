package nl.ahmed.features.favorites.domain.implementation.di

import dagger.Binds
import dagger.Module
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.favorites.domain.api.models.FavoriteBook
import nl.ahmed.features.favorites.domain.implementation.GetFavoritesBooksUseCase
import nl.ahmed.features.favorites.domain.implementation.RemoveFavoriteUseCase
import nl.ahmed.templates.kotlin.domain.NoArgUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@Module
abstract class FavoritesDomainModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsGetFavoritesBooksUseCase(
        getFavoritesBooksUseCase: GetFavoritesBooksUseCase
    ): NoArgUseCase<OperationResult<List<FavoriteBook>>>

    @FragmentScope
    @Binds
    internal abstract fun bindsRemoveFavoriteUseCase(
        removeFavoriteUseCase: RemoveFavoriteUseCase
    ): UseCase<FavoriteBook.Id, OperationResult<Unit>>
}
