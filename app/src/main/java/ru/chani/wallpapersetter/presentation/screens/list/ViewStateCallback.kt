package ru.chani.wallpapersetter.presentation.screens.list

import com.squareup.picasso.Callback
import java.lang.Exception

class ViewStateCallback(
    private val holder: ImageListRvAdapter.CustomViewHolder,
    private val onSuccess: (holder: ImageListRvAdapter.CustomViewHolder) -> Unit,
    private val onError: (holder: ImageListRvAdapter.CustomViewHolder) -> Unit
): Callback {
    override fun onSuccess() {
        onSuccess.invoke(holder)
    }

    override fun onError(e: Exception?) {
        onError.invoke(holder)
    }
}