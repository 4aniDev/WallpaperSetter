package ru.chani.wallpapersetter.presentation

sealed class ScreenState


object ScreenStateSplash: ScreenState()

object ScreenStateLoading: ScreenState()

object ScreenStateError: ScreenState()

object ScreenStateSuccess: ScreenState()
