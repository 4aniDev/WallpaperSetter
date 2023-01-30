package ru.chani.wallpapersetter.presentation

import androidx.fragment.app.Fragment
import ru.chani.wallpapersetter.domain.entity.Category

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun goBack()

    fun goToListFragment(category: Category)

}