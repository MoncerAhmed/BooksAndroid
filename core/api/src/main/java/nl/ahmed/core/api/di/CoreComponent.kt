package nl.ahmed.core.api.di

import android.content.Context
import retrofit2.Retrofit

interface CoreComponent {
    fun applicationContext(): Context

    fun retrofit(): Retrofit
}
