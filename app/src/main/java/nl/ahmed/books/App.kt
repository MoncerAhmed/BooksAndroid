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
import nl.ahmed.data.storage.api.daos.BooksDao
import nl.ahmed.data.storage.api.daos.ReviewsDao
import nl.ahmed.data.storage.api.entities.BookEntity
import nl.ahmed.data.storage.api.entities.ReviewEntity
import nl.ahmed.data.storage.implementation.di.DaggerDataStorageComponentImpl
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

    @Inject
    lateinit var booksDao: BooksDao

    @Inject
    lateinit var reviewsDao: ReviewsDao

    @Inject
    lateinit var bookEntityFactory: BookEntity.Factory

    @Inject
    lateinit var reviewEntityFactory: ReviewEntity.Factory

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
            val books = booksService.getBooks()
            booksService.getBook(books[5].id)
            val reviews = reviewsService.getReviews()

            booksDao.deleteAll()
            val _booksEntities = books.map { bookDto ->
                bookEntityFactory.create(
                    id = BookEntity.Id(bookDto.id.value),
                    createdAt = bookDto.createdAt,
                    author = bookDto.author,
                    coverUrl = bookDto.coverUrl,
                    description = bookDto.description,
                    reads = bookDto.reads,
                    reviews = bookDto.reviews,
                    summary = bookDto.summary
                )
            }
            booksDao.insertBooks(_booksEntities)
            val booksEntities = booksDao.getBooks()
            val book = booksDao.getBook(bookId = BookEntity.Id(books[5].id.value))

            val _reviewEntities = reviews.map { reviewDto ->
                reviewEntityFactory.create(
                    id = ReviewEntity.Id(reviewDto.id.value),
                    createdAt = reviewDto.createdAt,
                    reviewerName = reviewDto.reviewerName,
                    reviewerImgUrl = reviewDto.reviewerImgUrl,
                    review = reviewDto.review
                )
            }
            reviewsDao.deleteAll()
            reviewsDao.insertReviews(_reviewEntities)
            val reviewsEntities = reviewsDao.getReviews()
            val xx = reviewsEntities + reviewsEntities
        }

        println("This is from the app: $string")
        println("This is from the app: $retrofit")
        println("This is from the app: $booksService")
        println("This is from the app: $booksDao")
    }
}
