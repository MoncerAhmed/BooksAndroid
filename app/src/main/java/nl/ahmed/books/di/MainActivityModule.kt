package nl.ahmed.books.di

import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import nl.ahmed.books.AppNavigatorImpl
import nl.ahmed.books.R
import nl.ahmed.books.main.MainActivity
import nl.ahmed.core.api.di.ActivityScope
import nl.ahmed.navigation.AppNavigator

@Module
internal interface MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityProvidersModule::class,
            MainActivityBindersModule::class,
            FeaturesModule::class
        ]
    )
    fun injectMainActivity(): MainActivity

    @Module
    class MainActivityProvidersModule {
        @ActivityScope
        @Provides
        fun providesNavController(
            mainActivity: MainActivity
        ): NavController {
            return Navigation.findNavController(mainActivity, R.id.nav_host_fragment)
        }
    }

    @Module
    interface MainActivityBindersModule {
        @ActivityScope
        @Binds
        fun bindsAppNavigator(
            appNavigatorImpl: AppNavigatorImpl
        ): AppNavigator
    }
}
