package ru.chani.wallpapersetter.domain.usecase

import ru.chani.wallpapersetter.domain.Repository
import ru.chani.wallpapersetter.domain.entity.Category

class GetListOfCategoriesUseCase(private val repository: Repository) {

    operator fun invoke(): List<Category> = repository.getListOfCategories()

}