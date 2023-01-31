package ru.chani.wallpapersetter.data

import android.content.Context
import ru.chani.wallpapersetter.data.datasource.local.LocalDataSource
import ru.chani.wallpapersetter.data.datasource.remote.RemoteDataSource
import ru.chani.wallpapersetter.domain.Repository
import ru.chani.wallpapersetter.domain.entity.Category
import ru.chani.wallpapersetter.domain.entity.Image

class RepositoryImpl(private val context: Context) : Repository {

    private val localDataSource = LocalDataSource(context)
    private val remoteDataSource = RemoteDataSource()

    override fun getListOfCategories() = localDataSource.getListOfCategories()
    override suspend fun getListOfImagesByCategory(category: Category): List<Image> {
        return remoteDataSource.getListOfImagesByCategory(category = category)
    }


}