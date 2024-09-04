package nl.ahmed.feature.home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nl.ahmed.core.api.di.FragmentScope

@Module
abstract class HomeModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun injectHomeFragment(): HomeFragment
}
