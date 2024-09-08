package nl.ahmed.features.details.domain.implementation.di

import dagger.Binds
import dagger.Module
import javax.inject.Named
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.features.details.domain.api.DependencyQualifiers
import nl.ahmed.features.details.domain.api.models.BookDetails
import nl.ahmed.features.details.domain.implementation.AddFavoriteUseCase
import nl.ahmed.features.details.domain.implementation.GetBookDetailsUseCase
import nl.ahmed.features.details.domain.implementation.RemoveFavoriteUseCase
import nl.ahmed.templates.kotlin.domain.NoArgUseCase
import nl.ahmed.templates.kotlin.domain.UseCase

@Module
abstract class DetailsDomainModule {
    @FragmentScope
    @Binds
    internal abstract fun bindsGetBookDetailsUseCase(
        getBookDetailsUseCase: GetBookDetailsUseCase
    ): UseCase<BookDetails.Id, OperationResult<BookDetails>>

    @FragmentScope
    @Binds
    @Named(DependencyQualifiers.REMOVE_FAVORITE_USE_CASE)
    internal abstract fun bindsRemoveFavoriteUseCase(
        removeFavoriteUseCase: RemoveFavoriteUseCase
    ): UseCase<BookDetails.Id, OperationResult<Unit>>

    @FragmentScope
    @Binds
    @Named(DependencyQualifiers.ADD_FAVORITE_USE_CASE)
    internal abstract fun bindsAddFavoriteUseCase(
        addFavoriteUseCase: AddFavoriteUseCase
    ): UseCase<BookDetails.Id, OperationResult<Unit>>
}
