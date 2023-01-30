package ru.chani.wallpapersetter.domain

import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image

interface Repository {

    fun getListOfCategories(): List<Category>

    suspend fun getListOfImagesByCategory(category: Category): List<Image>

}