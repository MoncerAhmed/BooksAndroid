package nl.ahmed.dal.implementation.di

import dagger.Component
import nl.ahmed.common.kotlin.di.LoggerProvidingComponent
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.data.network.api.di.DataNetworkComponent
import nl.ahmed.data.storage.api.di.DataStorageComponent

@Component(
    dependencies = [DataNetworkComponent::class, DataStorageComponent::class, LoggerProvidingComponent::class],
    modules = [DataDalModule::class]
)
interface DataDalComponentImpl : DataDalComponent {
    @Component.Builder
    interface Builder {
        fun withDataNetworkComponent(dataNetworkComponent: DataNetworkComponent): Builder

        fun withDataStorageComponent(dataStorageComponent: DataStorageComponent): Builder

        fun withLoggerProvidingComponent(loggerProvidingComponent: LoggerProvidingComponent): Builder

        fun build(): DataDalComponent
    }
}
