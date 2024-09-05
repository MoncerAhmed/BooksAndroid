package nl.ahmed.templates.android

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class DaggerNavigatorOwnerActivity<N: FeatureNavigator> : DaggerAppCompatActivity() {
    @Inject
    internal lateinit var _navigator: N
    protected val navigator by lazy { _navigator }
}
