package ru.chani.wallpapersetter.presentation.categories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.chani.wallpapersetter.data.RepositoryImpl
import ru.chani.wallpapersetter.domain.Repository
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.usecase.GetListOfCategoriesUseCase

class CategoryViewModel(context: Context) : ViewModel() {

    private val repository: Repository = RepositoryImpl(context = context)
    private val getListOfCategoriesUseCase = GetListOfCategoriesUseCase(repository)

    private val _listOfCategories = MutableLiveData<List<Category>>(getListOfCategoriesUseCase())
    val listOfCategories: LiveData<List<Category>> = _listOfCategories


}