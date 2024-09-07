package nl.ahmed.core.di.modules

import dagger.Module
import dagger.Provides
import nl.ahmed.common.kotlin.di.CoreScope
import nl.ahmed.core.utils.LoggerImpl

@Module
internal class CoreModule {
    @CoreScope
    @Provides
    fun providesLogger(): nl.ahmed.common.kotlin.utils.Logger = LoggerImpl()
}
