package ru.chani.wallpapersetter.data.datasource.local

import ru.chani.wallpapersetter.R

object Categories {
    private val list = mutableListOf<CategoryModel>(
        CategoryModel(keyWord = "backgrounds", resId = R.string.backgrounds),
        CategoryModel(keyWord = "fashion", resId = R.string.fashion),
        CategoryModel(keyWord = "nature", resId = R.string.nature),
        CategoryModel(keyWord = "science", resId = R.string.science),
        CategoryModel(keyWord = "education", resId = R.string.education),
        CategoryModel(keyWord = "health", resId = R.string.health),
        CategoryModel(keyWord = "computer", resId = R.string.computer),
    )

    fun getListOfCategoryModels() = list

}