package nl.ahmed.templates.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class DaggerNavigatorOwnerActivity<N: FeatureNavigator> : AppCompatActivity() {
    @Inject
    internal lateinit var _navigator: N
    protected val navigator by lazy { _navigator }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectActivity()
        super.onCreate(savedInstanceState)
    }

    protected abstract fun injectActivity()
}
