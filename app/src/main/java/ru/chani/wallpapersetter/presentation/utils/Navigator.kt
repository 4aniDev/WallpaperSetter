package ru.chani.wallpapersetter.presentation.utils

import androidx.fragment.app.Fragment
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun goBack()

    fun goToListFragment(category: Category)
    fun goToImageItemFragment(imageItem: Image)

}