package nl.ahmed.books

import android.app.Application
import javax.inject.Inject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import nl.ahmed.books.di.AppComponent
import nl.ahmed.books.di.DaggerAppComponent
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.DaggerCoreComponentImpl
import nl.ahmed.data.network.api.services.BooksService
import nl.ahmed.data.network.api.services.ReviewsService
import nl.ahmed.data.network.implementation.di.DaggerDataNetworkComponentImpl
import retrofit2.Retrofit

internal class App : Application() {

    @Inject
    lateinit var string: String

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var booksService: BooksService

    @Inject
    lateinit var reviewsService: ReviewsService

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

        appComponent = DaggerAppComponent
            .builder()
            .withCoreComponent(coreComponent)
            .withDataNetworkComponent(dataNetworkComponent)
            .build()
        appComponent.inject(this)

        MainScope().launch {
            val books = booksService.getBooks()
            booksService.getBook(books[5].id)

            val reviews = reviewsService.getReviews()
        }

        println("This is from the app: $string")
        println("This is from the app: $retrofit")
        println("This is from the app: $booksService")
    }
}
