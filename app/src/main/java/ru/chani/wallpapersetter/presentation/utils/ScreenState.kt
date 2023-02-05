package ru.chani.wallpapersetter.presentation.utils

sealed class ScreenState

object ScreenStateLoading: ScreenState()
object ScreenStateError: ScreenState()
object ScreenStateSuccess: ScreenState()

