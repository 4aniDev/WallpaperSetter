package ru.chani.wallpapersetter.domain.usecase

import ru.chani.wallpapersetter.domain.Repository
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image

class GetListOfImagesByCategoryUseCase(private val repository: Repository) {
    suspend operator fun invoke(category: Category): List<Image> {
        return repository.getListOfImagesByCategory(category = category)
    }
}