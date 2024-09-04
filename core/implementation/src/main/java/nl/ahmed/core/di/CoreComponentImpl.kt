package nl.ahmed.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import nl.ahmed.core.api.di.CoreComponent
import nl.ahmed.core.api.di.CoreScope
import nl.ahmed.core.di.modules.CoreModule
import nl.ahmed.core.di.modules.NetworkingModule

@CoreScope
@Component(modules = [NetworkingModule::class, CoreModule::class])
interface CoreComponentImpl : CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withApplicationContext(applicationContext: Context): Builder

        @BindsInstance
        fun withBaseApiUrl(@Named("BASE_API_URL") baseApiUrl: String): Builder

        fun build(): CoreComponentImpl
    }
}
