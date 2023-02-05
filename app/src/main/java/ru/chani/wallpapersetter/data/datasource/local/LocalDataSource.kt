package ru.chani.wallpapersetter.data.datasource.local

import android.content.Context
import ru.chani.wallpapersetter.domain.entity.Category

class LocalDataSource(private val context: Context) {

    fun getListOfCategories(): List<Category> {
        val listOfCategoryModel = Categories.getListOfCategoryModels()

        val listOfCategories = mutableListOf<Category>()

        listOfCategoryModel.forEach { categoryModel ->
            listOfCategories.add(
                Category(
                    keyWord = categoryModel.keyWord,
                    name = context.getString(categoryModel.resId)
                )
            )
        }
        return listOfCategories
    }
}