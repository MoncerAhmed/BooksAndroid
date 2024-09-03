package nl.ahmed.data.storage.implementation.di

import dagger.Component
import javax.inject.Singleton
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.data.storage.api.di.DataStorageComponent
import nl.ahmed.data.storage.implementation.di.modules.DataStorageModule

@Singleton
@Component(
    dependencies = [CoreComponent::class],
    modules = [DataStorageModule::class, DataStorageModule::class]
)
interface DataStorageComponentImpl : DataStorageComponent  {

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun build(): DataStorageComponentImpl
    }
}
