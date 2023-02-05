package ru.chani.wallpapersetter.presentation.screens.list

import androidx.recyclerview.widget.DiffUtil
import ru.chani.wallpapersetter.domain.entity.Image

class ImageListDiffCallBack(
private val oldList: List<Image>,
private val newList: List<Image>
) : DiffUtil.Callback()
{

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.largeImageURL == newItem.largeImageURL
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAd = oldList[oldItemPosition]
        val newAd = newList[newItemPosition]
        return oldAd == newAd
    }
}