package nl.ahmed.books

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import nl.ahmed.books.di.app.AppComponent
import nl.ahmed.books.di.app.DaggerAppComponent
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.api.di.CoreComponentProvider
import nl.ahmed.core.di.DaggerCoreComponentImpl
import nl.ahmed.dal.implementation.di.DaggerDataDalComponentImpl
import nl.ahmed.data.dal.di.DataDalComponent
import nl.ahmed.data.dal.di.DataDalComponentProvider
import nl.ahmed.data.network.implementation.di.DaggerDataNetworkComponentImpl
import nl.ahmed.data.storage.implementation.di.DaggerDataStorageComponentImpl

internal class App : DaggerApplication(), DataDalComponentProvider, CoreComponentProvider {

    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val coreComponent: CoreComponent = DaggerCoreComponentImpl
            .builder()
            .withApplicationContext(this)
            .withBaseApiUrl(BuildConfig.BASE_API)
            .build()

        val dataNetworkComponent = DaggerDataNetworkComponentImpl
            .builder()
            .withCoreComponent(coreComponent)
            .build()

        val dataStorageComponent = DaggerDataStorageComponentImpl
            .builder()
            .withCoreComponent(coreComponent)
            .build()

        val dataDalComponent = DaggerDataDalComponentImpl
            .builder()
            .withDataNetworkComponent(dataNetworkComponent)
            .withDataStorageComponent(dataStorageComponent)
            .withLoggerProvidingComponent(coreComponent)
            .build()

        appComponent = DaggerAppComponent
            .builder()
            .withCoreComponent(coreComponent)
            .withDataDalComponent(dataDalComponent)
            .build()
        return appComponent
    }

    internal fun getAppComponent(): AppComponent {
        return appComponent
    }

    override fun getDataDalComponent(): DataDalComponent {
        return appComponent.dataDalComponent()
    }

    override fun getCoreComponent(): CoreComponent {
        return appComponent.coreComponent()
    }
}
