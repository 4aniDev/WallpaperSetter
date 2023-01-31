package ru.chani.wallpapersetter.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.presentation.categories.CategoriesFragment
import ru.chani.wallpapersetter.presentation.list.ListFragment

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchFragment(CategoriesFragment.newInstance())
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
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
        Log.d("GO_TO_IMAGE_ITEM_DEBUG", imageItem.largeImageURL)
    }
}