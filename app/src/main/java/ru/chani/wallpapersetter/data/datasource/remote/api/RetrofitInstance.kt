package ru.chani.wallpapersetter.data.datasource.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pixabay.com/"

object RetrofitInstance {

    private val instance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api: Api = instance.create(Api::class.java)

}