package nl.ahmed.templates.android

import javax.inject.Inject
import javax.inject.Provider
import nl.ahmed.navigation.AppNavigator
import nl.ahmed.templates.kotlin.mvi.Navigator

abstract class FeatureNavigator : Navigator {
    @Inject
    internal lateinit var _appNavigator: Provider<AppNavigator>
    protected val appNavigator: AppNavigator by lazy { _appNavigator.get() }

    override fun navigateUp(): Boolean {
        return appNavigator.navigateUp()
    }
}
