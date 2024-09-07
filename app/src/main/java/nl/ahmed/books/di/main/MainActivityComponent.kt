package nl.ahmed.books.di.main

import dagger.BindsInstance
import dagger.Component
import nl.ahmed.books.di.app.AppComponent
import nl.ahmed.books.main.MainActivity
import nl.ahmed.common.kotlin.di.ActivityScope
import nl.ahmed.navigation.di.AppNavigatorComponent

@ActivityScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        FeaturesModule::class,
        MainActivityModule::class
    ]
)
internal interface MainActivityComponent : AppNavigatorComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        fun withAppComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun withMainActivity(mainActivity: MainActivity): Builder

        fun build(): MainActivityComponent
    }
}
