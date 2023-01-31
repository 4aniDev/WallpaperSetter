package ru.chani.wallpapersetter.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val keyWord: String, val name: String): Parcelable
