package nl.ahmed.books.di

import dagger.Component
import nl.ahmed.books.App
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.AppScope
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.data.network.api.di.DataNetworkComponent
import nl.ahmed.data.storage.api.di.DataStorageComponent

@AppScope
@Component(
    dependencies = [
        CoreComponent::class,
        DataDalComponent::class,
    ],
    modules = [AppModule::class]
)
internal interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        /*fun withDataNetworkComponent(dataNetworkComponent: DataNetworkComponent): Builder

        fun withDataStorageComponent(dataStorageComponent: DataStorageComponent): Builder*/

        fun withDataDalComponent(dataDalComponent: DataDalComponent): Builder

        fun build(): AppComponent
    }
}
