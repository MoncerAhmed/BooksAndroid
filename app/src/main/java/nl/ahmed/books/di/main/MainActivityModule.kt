package nl.ahmed.books.di.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import nl.ahmed.books.AppNavigatorImpl
import nl.ahmed.books.R
import nl.ahmed.books.main.MainActivity
import nl.ahmed.common.kotlin.di.ActivityScope
import nl.ahmed.navigation.AppNavigator

@Module(
    includes = [
        MainActivityModule.MainActivityProvidersModule::class,
        MainActivityModule.MainActivityBindersModule::class
    ]
)
internal interface MainActivityModule {

    @Module
    class MainActivityProvidersModule {
        @ActivityScope
        @Provides
        fun providesNavHostFragment(
            mainActivity: MainActivity
        ): NavHostFragment = mainActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        @ActivityScope
        @Provides
        fun providesNavController(
            navHostFragment: NavHostFragment
        ): NavController {
            return navHostFragment.navController
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
