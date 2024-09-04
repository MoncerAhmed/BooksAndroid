package nl.ahmed.core.api.di

import android.content.Context
import nl.ahmed.common.kotlin.di.LoggerProvidingComponent
import retrofit2.Retrofit

interface CoreComponent : LoggerProvidingComponent {
    fun applicationContext(): Context

    fun retrofit(): Retrofit
}
