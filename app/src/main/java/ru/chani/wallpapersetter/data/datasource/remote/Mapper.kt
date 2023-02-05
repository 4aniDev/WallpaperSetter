package ru.chani.wallpapersetter.data.datasource.remote

import ru.chani.wallpapersetter.data.datasource.remote.dto.Hit
import ru.chani.wallpapersetter.domain.entity.Image

class Mapper {

    fun fromHitToImage(hit: Hit): Image {
        return Image(largeImageURL = hit.largeImageURL, previewURL = hit.previewURL)
    }

}