package ru.chani.wallpapersetter.data.datasource.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.chani.wallpapersetter.data.datasource.remote.dto.ListOfImages

interface Api {

    @GET("api")
    suspend fun getListOfImagesByCategory(
        @Query("key") apiKey: String,
        @Query("category") categoryName: String
    ): Response<ListOfImages>

}