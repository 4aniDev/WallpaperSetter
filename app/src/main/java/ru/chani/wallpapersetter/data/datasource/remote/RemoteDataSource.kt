package ru.chani.wallpapersetter.data.datasource.remote

import ru.chani.wallpapersetter.data.datasource.remote.api.API_KEY
import ru.chani.wallpapersetter.data.datasource.remote.api.RetrofitInstance
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image

class RemoteDataSource {

    private val mapper = Mapper()

    suspend fun getListOfImagesByCategory(category: Category): List<Image> {
        val response = RetrofitInstance.api.getListOfImagesByCategory(
            apiKey = API_KEY,
            categoryKeyWord = category.keyWord
        )

        val listOfImages = mutableListOf<Image>()
        response.body()?.hits?.forEach { hit -> listOfImages.add(mapper.fromHitToImage(hit = hit)) }

        return listOfImages
    }
}