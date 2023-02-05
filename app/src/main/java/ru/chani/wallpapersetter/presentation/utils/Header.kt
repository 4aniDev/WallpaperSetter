package ru.chani.wallpapersetter.presentation.utils

import androidx.fragment.app.Fragment

fun Fragment.header() = requireActivity() as Header

interface Header {

    fun hideHeader()

    fun showText(string: String)

}