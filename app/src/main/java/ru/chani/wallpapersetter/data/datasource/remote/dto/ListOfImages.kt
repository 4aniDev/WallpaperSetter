package ru.chani.wallpapersetter.data.datasource.remote.dto

data class ListOfImages(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)