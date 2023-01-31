package ru.chani.wallpapersetter.presentation.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chani.wallpapersetter.data.RepositoryImpl
import ru.chani.wallpapersetter.domain.Repository
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.domain.usecase.GetListOfImagesByCategoryUseCase

class ListViewModel(context: Context, category: Category) : ViewModel() {

    private val repository: Repository = RepositoryImpl(context = context)
    private val getListOfImagesByCategoryUseCase = GetListOfImagesByCategoryUseCase(repository)

    private val _listOfImages = MutableLiveData<List<Image>>()
    val listOfImages: LiveData<List<Image>> = _listOfImages

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _listOfImages.postValue(getListOfImagesByCategoryUseCase(category))
        }
    }
}