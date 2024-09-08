package nl.ahmed.features.details.shared.di

import dagger.Component
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.features.details.shared.DetailsFragment
import nl.ahmed.features.details.data.di.DetailsDataModule
import nl.ahmed.features.details.domain.implementation.di.DetailsDomainModule
import nl.ahmed.features.details.presentation.implementation.di.DetailsPresentationModule
import nl.ahmed.features.details.ui.di.DetailsUiModule
import nl.ahmed.navigation.di.AppNavigatorComponent

@FragmentScope
@Component(
    dependencies = [
        CoreComponent::class,
        AppNavigatorComponent::class,
        DataDalComponent::class,
    ],
    modules = [
        DetailsSharedModule::class,
        DetailsUiModule::class,
        DetailsPresentationModule::class,
        DetailsDataModule::class,
        DetailsDomainModule::class
    ]
)
internal interface DetailsComponent {
    fun inject(detailsFragment: DetailsFragment)

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun withAppNavigatorComponent(appNavigatorComponent: AppNavigatorComponent): Builder

        fun withDataDalComponent(dataDalComponent: DataDalComponent): Builder

        fun build(): DetailsComponent
    }
}
