package ru.chani.wallpapersetter.presentation.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.presentation.screens.categories.CategoryViewModel
import ru.chani.wallpapersetter.presentation.screens.imageItem.ImageViewModel
import ru.chani.wallpapersetter.presentation.screens.list.ListViewModel

class AppViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private var category: Category? = null

    constructor(context: Context, _category: Category) : this(context) {
        category = _category
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(context = context) as T
        }
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(context = context, category = category!!) as T
        }
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(context = context) as T
        } else {
            throw IllegalArgumentException("ViewModel class Not found")
        }
    }

}