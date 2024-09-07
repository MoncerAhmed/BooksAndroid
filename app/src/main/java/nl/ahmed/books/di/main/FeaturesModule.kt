package nl.ahmed.books.di.main

import dagger.Module
import nl.ahmed.features.favorites.FavoritesModule

@Module(
    includes = [
        FavoritesModule::class
    ]
)
internal interface FeaturesModule
