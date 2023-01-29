package ru.chani.wallpapersetter.data

import android.content.Context
import ru.chani.wallpapersetter.data.datasource.local.LocalDataSource
import ru.chani.wallpapersetter.domain.Repository

class RepositoryImpl(private val context: Context) : Repository {

    private val localDataSource = LocalDataSource(context)

    override fun getListOfCategories() = localDataSource.getListOfCategories()

}