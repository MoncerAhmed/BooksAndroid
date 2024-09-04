package nl.ahmed.books.di

import dagger.Module
import nl.ahmed.feature.home.HomeModule
import nl.ahmed.features.favorites.FavoritesModule

@Module(
    includes = [
        HomeModule::class,
        FavoritesModule::class
    ]
)
internal interface FeaturesModule
