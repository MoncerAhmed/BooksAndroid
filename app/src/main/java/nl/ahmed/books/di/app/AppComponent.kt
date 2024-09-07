package nl.ahmed.books.di.app

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import nl.ahmed.books.App
import nl.ahmed.books.di.main.MainActivityModule
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.common.kotlin.di.AppScope
import nl.ahmed.data.dal.di.DataDalComponent

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
internal interface AppComponent : AndroidInjector<App> {

    fun coreComponent(): CoreComponent

    fun dataDalComponent(): DataDalComponent

    @Component.Builder
    interface Builder {
        fun withCoreComponent(coreComponent: CoreComponent): Builder

        fun withDataDalComponent(dataDalComponent: DataDalComponent): Builder

        fun build(): AppComponent
    }
}
