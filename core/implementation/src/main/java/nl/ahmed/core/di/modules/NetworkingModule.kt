package nl.ahmed.core.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.time.ZonedDateTime
import javax.inject.Named
import nl.ahmed.common.android.converters.retrofit.LocalDateTimeAdapter
import nl.ahmed.core.api.di.CoreScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
internal class NetworkingModule {

    @CoreScope
    @Provides
    fun providesGson(): Gson = GsonBuilder()
        .registerTypeAdapter(
            ZonedDateTime::class.java,
            LocalDateTimeAdapter
        )
        .create()

    @CoreScope
    @Provides
    fun providesConverterFactory(
        gson: Gson
    ): Converter.Factory = GsonConverterFactory.create(gson)

    @CoreScope
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @CoreScope
    @Provides
    fun providesOkhttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @CoreScope
    @Provides
    fun providesRetrofit(
        @Named("BASE_API_URL") baseApiUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ) = Retrofit
        .Builder()
        .baseUrl(baseApiUrl)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
}
