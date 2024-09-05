package nl.ahmed.templates.android

import javax.inject.Inject
import javax.inject.Provider
import nl.ahmed.navigation.AppNavigator

abstract class FeatureNavigator {
    @Inject
    internal lateinit var _appNavigator: Provider<AppNavigator>
    protected val appNavigator: AppNavigator by lazy { _appNavigator.get() }

    fun navigateUp(): Boolean {
        return appNavigator.navigateUp()
    }
}
