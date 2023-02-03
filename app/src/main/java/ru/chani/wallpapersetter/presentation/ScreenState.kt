package ru.chani.wallpapersetter.presentation

sealed class ScreenState

object ScreenStateLoading: ScreenState()
object ScreenStateError: ScreenState()
object ScreenStateSuccess: ScreenState()

