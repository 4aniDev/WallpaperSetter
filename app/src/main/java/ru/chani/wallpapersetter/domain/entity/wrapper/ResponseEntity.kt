package ru.chani.wallpapersetter.domain.entity.wrapper

data class ResponseEntity<T>(
    val isSuccessful: Boolean,
    val body: T
)