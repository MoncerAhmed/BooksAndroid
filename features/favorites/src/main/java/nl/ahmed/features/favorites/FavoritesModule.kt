package nl.ahmed.features.favorites

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nl.ahmed.common.kotlin.di.FragmentScope

@Module
abstract class FavoritesModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun injectFavoritesFragment(): FavoritesFragment
}
