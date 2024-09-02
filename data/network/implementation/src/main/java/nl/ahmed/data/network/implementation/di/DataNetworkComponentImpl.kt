package nl.ahmed.data.network.implementation.di

import dagger.Component
import javax.inject.Singleton
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.data.network.api.di.DataNetworkComponent

@Singleton
@Component(dependencies = [CoreComponent::class], modules = [DataNetworkModule::class])
interface DataNetworkComponentImpl : DataNetworkComponent {

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun build(): DataNetworkComponentImpl
    }
}
