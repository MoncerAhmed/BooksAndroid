package nl.ahmed.books.main

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import nl.ahmed.books.App
import nl.ahmed.books.R
import nl.ahmed.books.databinding.ActivityMainBinding
import nl.ahmed.books.di.main.DaggerMainActivityComponent
import nl.ahmed.books.di.main.MainActivityComponent
import nl.ahmed.navigation.di.AppNavigatorComponent
import nl.ahmed.navigation.di.AppNavigatorComponentProvider
import nl.ahmed.templates.android.DaggerNavigatorOwnerActivity

internal class MainActivity : DaggerNavigatorOwnerActivity<MainNavigator>(), AppNavigatorComponentProvider {

    private lateinit var mainActivityComponent: MainActivityComponent

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun injectActivity() {
        mainActivityComponent =DaggerMainActivityComponent
            .builder()
            .withAppComponent((application as App).getAppComponent())
            .withMainActivity(this)
            .build()
        mainActivityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreenDuration()
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        enableEdgeToEdge()
        setContentView(binding.root)
        setupBottomNavigationBar()
        onBackPressedDispatcher.addCallback(owner = this) {
            if (!navigator.navigateUp()) {
                finish()
            }
        }
    }

    private fun setupSplashScreenDuration() {
        var keepSplashScreen = true
        val delay = 2500L
        installSplashScreen().setKeepOnScreenCondition { keepSplashScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashScreen = false }, delay)
    }

    private fun setupBottomNavigationBar() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadHomeFragment()
                    true
                }
                R.id.favorite -> {
                    loadFavoriteFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun loadHomeFragment() {
        navigator.navigateToHome()
    }

    private fun loadFavoriteFragment() {
        navigator.navigateToFavorite()
    }

    override fun getAppNavigatorComponent(): AppNavigatorComponent {
        return mainActivityComponent
    }
}
