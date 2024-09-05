package nl.ahmed.books.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import nl.ahmed.books.R
import nl.ahmed.books.databinding.ActivityMainBinding
import nl.ahmed.templates.android.DaggerNavigatorOwnerActivity

internal class MainActivity : DaggerNavigatorOwnerActivity<MainNavigator>() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreenDuration()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupBottomNavigationBar()
        onBackPressedDispatcher.addCallback(owner = this) {
            if (!navigator.navigateUp()) {
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("This is from the activity: $navigator")
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
}
