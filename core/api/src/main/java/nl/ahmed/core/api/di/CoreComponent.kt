package nl.ahmed.core.api.di

import android.content.Context
import nl.ahmed.common.kotlin.utils.Logger
import retrofit2.Retrofit

interface CoreComponent {
    fun applicationContext(): Context

    fun retrofit(): Retrofit

    fun logger(): Logger
}
