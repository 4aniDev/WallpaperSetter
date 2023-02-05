package ru.chani.wallpapersetter.data.datasource.remote

import ru.chani.wallpapersetter.data.datasource.remote.api.API_KEY
import ru.chani.wallpapersetter.data.datasource.remote.api.RetrofitInstance
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image
import ru.chani.wallpapersetter.domain.entity.wrapper.ErrorEntity
import ru.chani.wallpapersetter.domain.entity.wrapper.ResponseEntity

class RemoteDataSource {

    private val mapper = Mapper()

    suspend fun getListOfImagesByCategory(category: Category): ResponseEntity<List<Image>> {

        try {
            val response = RetrofitInstance.api.getListOfImagesByCategory(
                apiKey = API_KEY,
                categoryKeyWord = category.keyWord
            )

            if (response.isSuccessful) {
                val listOfImages = mutableListOf<Image>()
                response.body()?.hits?.forEach { hit -> listOfImages.add(mapper.fromHitToImage(hit = hit)) }
                return ResponseEntity(isSuccessful = true, body = listOfImages)
            } else {
                throw ErrorEntity(response.errorBody().toString())
            }

        } catch (e: Exception) {
            throw ErrorEntity(e.stackTraceToString())
        }

    }
}