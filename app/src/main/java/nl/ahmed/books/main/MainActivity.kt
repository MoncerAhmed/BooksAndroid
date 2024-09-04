package nl.ahmed.books.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Provider
import nl.ahmed.books.R
import nl.ahmed.books.databinding.ActivityMainBinding
import nl.ahmed.navigation.AppNavigator

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var appNavigator: Provider<AppNavigator>

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreenDuration()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setupBottomNavigationBar()
    }

    override fun onResume() {
        super.onResume()
        println("This is from the activity: ${appNavigator.get()}")
    }

    private fun setupSplashScreenDuration() {
        var keepSplashScreen = true
        val delay = 2500L
        installSplashScreen().setKeepOnScreenCondition { keepSplashScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashScreen = false }, delay)
    }

    private fun setupBottomNavigationBar() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
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
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_home)
    }

    private fun loadFavoriteFragment() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_favorites)
    }
}
