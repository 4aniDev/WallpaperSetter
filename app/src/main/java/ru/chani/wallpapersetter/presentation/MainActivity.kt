package ru.chani.wallpapersetter.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.presentation.categories.CategoriesFragment
import ru.chani.wallpapersetter.presentation.imageItem.ImageIItemFragment
import ru.chani.wallpapersetter.presentation.list.ListFragment

class MainActivity : AppCompatActivity(), Navigator {

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            val restoredFragment =
                supportFragmentManager.getFragment(savedInstanceState, KEY_CURRENT_FRAGMENT)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, restoredFragment!!)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CategoriesFragment.newInstance())
                .commit()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)!!
        supportFragmentManager.putFragment(outState, KEY_CURRENT_FRAGMENT, currentFragment)
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

    companion object {
        private const val KEY_CURRENT_FRAGMENT = "KEY_CURRENT_FRAGMENT"
    }
}