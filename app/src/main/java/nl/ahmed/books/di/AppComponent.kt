package nl.ahmed.books.di

import dagger.Component
import nl.ahmed.books.App
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.AppScope
import nl.ahmed.data.network.api.di.DataNetworkComponent

@AppScope
@Component(
    dependencies = [CoreComponent::class, DataNetworkComponent::class],
    modules = [AppModule::class]
)
internal interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun withDataNetworkComponent(dataNetworkComponent: DataNetworkComponent): Builder

        fun build(): AppComponent
    }
}
