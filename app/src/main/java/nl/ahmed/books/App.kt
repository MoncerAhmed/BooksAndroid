package nl.ahmed.books

import android.app.Application
import javax.inject.Inject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import nl.ahmed.books.di.AppComponent
import nl.ahmed.books.di.DaggerAppComponent
import nl.ahmed.common.kotlin.operation.CashedFetchOperationExecutor
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.DaggerCoreComponentImpl
import nl.ahmed.data.network.api.dtos.BookDto
import nl.ahmed.data.network.api.services.BooksService
import nl.ahmed.data.network.implementation.di.DaggerDataNetworkComponentImpl
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.implementation.di.DaggerDataStorageComponentImpl

internal class App : Application() {

    @Inject
    lateinit var operationExecutor: CashedFetchOperationExecutor<BookDto, BookEntity, Book>

    @Inject
    lateinit var booksService: BooksService

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

        appComponent = DaggerAppComponent
            .builder()
            .withCoreComponent(coreComponent)
            .withDataNetworkComponent(dataNetworkComponent)
            .withDataStorageComponent(dataStorageComponent)
            .build()
        appComponent.inject(this)

        MainScope().launch {
            val books = operationExecutor.execute {
                booksService.getAll()
            }

            println("This is from the app $books")
        }
    }
}
