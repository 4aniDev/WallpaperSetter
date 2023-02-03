package ru.chani.wallpapersetter.presentation.imageItem

import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.presentation.ScreenState
import ru.chani.wallpapersetter.presentation.ScreenStateError
import ru.chani.wallpapersetter.presentation.ScreenStateLoading
import ru.chani.wallpapersetter.presentation.ScreenStateSuccess

class ImageViewModel(private val context: Context) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>(ScreenStateLoading)
    val screenState: LiveData<ScreenState> = _screenState


    private val _currentImage = MutableLiveData<Image>()
    val currentImage: LiveData<Image> = _currentImage

    fun setCurrentImage(image: Image) {
        _currentImage.value = image
    }

    fun setWallpaper(drawable: Drawable) {
        viewModelScope.launch(Dispatchers.IO) {
            WallpaperManager.getInstance(context).setBitmap(drawable.toBitmap())
        }
    }


    fun setStateSuccess() {
        _screenState.value = ScreenStateSuccess
    }

    fun setStateError() {
        _screenState.value = ScreenStateError
    }

}