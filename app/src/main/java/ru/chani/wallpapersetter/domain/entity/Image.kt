package ru.chani.wallpapersetter.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val largeImageURL: String,
    val previewURL: String
): Parcelable
