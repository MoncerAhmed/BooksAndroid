package nl.ahmed.features.favorites.shared.di

import dagger.Component
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.features.favorites.shared.FavoritesFragment
import nl.ahmed.features.favorites.data.di.FavoritesDataModule
import nl.ahmed.features.favorites.domain.implementation.di.FavoritesDomainModule
import nl.ahmed.features.favorites.presentation.implementation.di.FavoritesPresentationModule
import nl.ahmed.features.favorites.ui.di.FavoritesUiModule
import nl.ahmed.navigation.di.AppNavigatorComponent

@FragmentScope
@Component(
    dependencies = [
        CoreComponent::class,
        AppNavigatorComponent::class,
        DataDalComponent::class,
    ],
    modules = [
        FavoritesSharedModule::class,
        FavoritesUiModule::class,
        FavoritesPresentationModule::class,
        FavoritesDataModule::class,
        FavoritesDomainModule::class
    ]
)
internal interface FavoritesComponent {
    fun inject(favoritesFragment: FavoritesFragment)

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun withAppNavigatorComponent(appNavigatorComponent: AppNavigatorComponent): Builder

        fun withDataDalComponent(dataDalComponent: DataDalComponent): Builder

        fun build(): FavoritesComponent
    }
}
