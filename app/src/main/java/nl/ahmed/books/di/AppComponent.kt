package nl.ahmed.books.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import nl.ahmed.books.App
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.api.di.AppScope
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.feature.home.HomeModule

@AppScope
@Component(
    dependencies = [
        CoreComponent::class,
        DataDalComponent::class,
    ],
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
internal interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun withDataDalComponent(dataDalComponent: DataDalComponent): Builder

        fun build(): AppComponent
    }
}
