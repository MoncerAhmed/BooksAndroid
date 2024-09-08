package nl.ahmed.features.home.shared.di

import dagger.Component
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.features.home.shared.HomeFragment
import nl.ahmed.features.home.data.di.HomeDataModule
import nl.ahmed.features.home.domain.implementation.di.HomeDomainModule
import nl.ahmed.features.home.presentation.implementation.di.HomePresentationModule
import nl.ahmed.features.home.ui.di.HomeUiModule
import nl.ahmed.navigation.di.AppNavigatorComponent

@FragmentScope
@Component(
    dependencies = [
        CoreComponent::class,
        AppNavigatorComponent::class,
        DataDalComponent::class,
    ],
    modules = [
        HomeSharedModule::class,
        HomeUiModule::class,
        HomePresentationModule::class,
        HomeDataModule::class,
        HomeDomainModule::class
    ]
)
internal interface HomeComponent {
    fun inject(homeFragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun withAppNavigatorComponent(appNavigatorComponent: AppNavigatorComponent): Builder

        fun withDataDalComponent(dataDalComponent: DataDalComponent): Builder

        fun build(): HomeComponent
    }
}
