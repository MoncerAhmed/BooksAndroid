package nl.ahmed.books

import android.app.Application
import javax.inject.Inject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import nl.ahmed.books.di.AppComponent
import nl.ahmed.books.di.DaggerAppComponent
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.common.kotlin.utils.Logger
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.DaggerCoreComponentImpl
import nl.ahmed.dal.implementation.di.DaggerDataDalComponentImpl
import nl.ahmed.data.dal.repositories.BooksRepository
import nl.ahmed.data.dal.repositories.FavoritesRepository
import nl.ahmed.data.network.implementation.di.DaggerDataNetworkComponentImpl
import nl.ahmed.data.storage.implementation.di.DaggerDataStorageComponentImpl

internal class App : Application() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var booksRepository: BooksRepository

    @Inject
    lateinit var favoritesRepository: FavoritesRepository

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
            when(val result = booksRepository.getBooks(keyword = "")) {
                is OperationResult.Success -> {
                    logger.logInfo("This is from the app ${result.data}")
                    favoritesRepository.addFavorite(result.data[5].id)
                    favoritesRepository.addFavorite(result.data[6].id)

                    favoritesRepository.deleteFavorite(result.data[6].id)
                }
                is OperationResult.Failure -> logger.logError(
                    throwable = result.throwable,
                    message = "Error"
                )
            }

            when(val result = favoritesRepository.getFavorites()) {
                is OperationResult.Success -> {
                    logger.logInfo("This are favorites ${result.data}")
                }
                is OperationResult.Failure -> logger.logError(
                    throwable = result.throwable,
                    message = "Error"
                )
            }
        }
    }
}
