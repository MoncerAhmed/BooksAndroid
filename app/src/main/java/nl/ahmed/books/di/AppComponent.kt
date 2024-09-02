package nl.ahmed.books.di

import dagger.Component
import nl.ahmed.books.App
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.AppScope

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
internal interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun build(): AppComponent
    }
}
