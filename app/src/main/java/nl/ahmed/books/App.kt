package nl.ahmed.books

import android.app.Application
import javax.inject.Inject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import nl.ahmed.books.di.AppComponent
import nl.ahmed.books.di.DaggerAppComponent
import nl.ahmed.common.kotlin.utils.Logger
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.DaggerCoreComponentImpl
import nl.ahmed.dal.implementation.di.DaggerDataDalComponentImpl
import nl.ahmed.data.dal.BooksRepository
import nl.ahmed.data.network.implementation.di.DaggerDataNetworkComponentImpl
import nl.ahmed.data.storage.implementation.di.DaggerDataStorageComponentImpl

internal class App : Application() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var booksRepository: BooksRepository

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

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
        appComponent.inject(this)

        MainScope().launch {
            val books = booksRepository.getBooks(keyword = "")

            logger.logError("This is from the app $books")
        }
    }
}
