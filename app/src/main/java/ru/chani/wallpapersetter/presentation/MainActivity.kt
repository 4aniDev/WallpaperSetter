package ru.chani.wallpapersetter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.presentation.screens.SplashFragment
import ru.chani.wallpapersetter.presentation.screens.categories.CategoriesFragment
import ru.chani.wallpapersetter.presentation.screens.imageItem.ImageIItemFragment
import ru.chani.wallpapersetter.presentation.screens.list.ListFragment
import ru.chani.wallpapersetter.presentation.utils.Header
import ru.chani.wallpapersetter.presentation.utils.Navigator

class MainActivity : AppCompatActivity(), Navigator, Header {

    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideHeader()
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val restoredFragment =
                supportFragmentManager.getFragment(savedInstanceState, KEY_CURRENT_FRAGMENT)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, restoredFragment!!)
                .commit()
        } else {
            activityScope.launch {
                launchSplashFragment()
                delay(SHOW_SPLASH_TIME)
                launchFirstFragment()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)!!
        supportFragmentManager.putFragment(outState, KEY_CURRENT_FRAGMENT, currentFragment)
    }

    override fun onDestroy() {
        activityScope.cancel()
        super.onDestroy()
    }

    private fun launchSplashFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SplashFragment.newInstance())
            .commit()
    }

    private fun launchFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CategoriesFragment.newInstance())
            .commit()
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun goToListFragment(category: Category) {
        val listFragment = ListFragment.newInstance(category = category)
        launchFragment(listFragment)
    }

    override fun goToImageItemFragment(imageItem: Image) {
        val imageIItemFragment = ImageIItemFragment.newInstance(image = imageItem)
        launchFragment(imageIItemFragment)
    }

    override fun hideHeader() {
        supportActionBar?.hide()
    }

    override fun showText(string: String) {
        supportActionBar?.show()
        this.title = string
    }

    companion object {
        private const val KEY_CURRENT_FRAGMENT = "KEY_CURRENT_FRAGMENT"
        private const val SHOW_SPLASH_TIME = 500L
    }
}