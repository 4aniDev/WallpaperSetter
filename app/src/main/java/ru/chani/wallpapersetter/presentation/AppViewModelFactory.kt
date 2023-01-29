package ru.chani.wallpapersetter.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.chani.wallpapersetter.presentation.categories.CategoryViewModel

class AppViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(context) as T
    }

}