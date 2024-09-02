package nl.ahmed.books

import android.app.Application
import javax.inject.Inject
import nl.ahmed.books.di.AppComponent
import nl.ahmed.books.di.DaggerAppComponent
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.di.DaggerCoreComponentImpl
import retrofit2.Retrofit

internal class App : Application() {

    @Inject
    lateinit var string: String

    @Inject
    lateinit var retrofit: Retrofit

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        val coreComponent: CoreComponent = DaggerCoreComponentImpl
            .builder()
            .withApplicationContext(this)
            .withBaseApiUrl(BuildConfig.BASE_API)
            .build()

        appComponent = DaggerAppComponent
            .builder()
            .withCoreComponent(coreComponent)
            .build()
        appComponent.inject(this)

        println("This is from the app: $string")
        println("This is from the app: $retrofit")
    }
}
