package ru.chani.wallpapersetter.presentation.screens.list

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
import ru.chani.wallpapersetter.domain.entity.wrapper.ErrorEntity
import ru.chani.wallpapersetter.domain.usecase.GetListOfImagesByCategoryUseCase
import ru.chani.wallpapersetter.presentation.utils.ScreenState
import ru.chani.wallpapersetter.presentation.utils.ScreenStateError
import ru.chani.wallpapersetter.presentation.utils.ScreenStateLoading
import ru.chani.wallpapersetter.presentation.utils.ScreenStateSuccess

class ListViewModel(context: Context, category: Category) : ViewModel() {

    private val repository: Repository = RepositoryImpl(context = context)
    private val getListOfImagesByCategoryUseCase = GetListOfImagesByCategoryUseCase(repository)

    private val _screenState = MutableLiveData<ScreenState>(ScreenStateLoading)
    val screenState: LiveData<ScreenState> = _screenState

    private val _listOfImages = MutableLiveData<List<Image>>()
    val listOfImages: LiveData<List<Image>> = _listOfImages

    init {
        viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = getListOfImagesByCategoryUseCase(category)
                    if (response.isSuccessful) {
                        _listOfImages.postValue(response.body)
                        _screenState.postValue(ScreenStateSuccess)
                    }
                } catch (e: ErrorEntity) {
                    _screenState.postValue(ScreenStateError)
                }
        }
    }

}