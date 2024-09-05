package nl.ahmed.books.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
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
