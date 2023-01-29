package ru.chani.wallpapersetter.domain

import ru.chani.wallpapersetter.domain.entity.Category

interface Repository {

    fun getListOfCategories(): List<Category>

}