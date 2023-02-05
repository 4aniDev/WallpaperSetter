package ru.chani.wallpapersetter.domain

import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.domain.entity.wrapper.ResponseEntity

interface Repository {

    fun getListOfCategories(): List<Category>

    suspend fun getListOfImagesByCategory(category: Category): ResponseEntity<List<Image>>

}