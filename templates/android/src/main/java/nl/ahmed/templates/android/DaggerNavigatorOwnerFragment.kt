package nl.ahmed.templates.android

import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class DaggerNavigatorOwnerFragment<N: FeatureNavigator> : DaggerFragment() {
    @Inject
    internal lateinit var _navigator: N
    protected val navigator by lazy { _navigator }
}
