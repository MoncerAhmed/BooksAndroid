package nl.ahmed.core.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Named
import nl.ahmed.core.di.CoreScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
internal class NetworkingModule {

    @CoreScope
    @Provides
    fun providesConverterFactory(): Converter.Factory = GsonConverterFactory.create()

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
