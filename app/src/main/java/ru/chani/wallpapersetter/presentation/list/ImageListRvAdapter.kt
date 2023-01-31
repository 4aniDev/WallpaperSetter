package ru.chani.wallpapersetter.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.chani.wallpapersetter.R
import ru.chani.wallpapersetter.databinding.ImageItemBinding
import ru.chani.wallpapersetter.domain.entity.Image


class ImageListRvAdapter : RecyclerView.Adapter<ImageListRvAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var onItemClickListener: ((Image) -> Unit)? = null

    private var listOfImages = emptyList<Image>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CustomViewHolder(binding = binding)
    }

    override fun getItemCount() = listOfImages.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentImage = listOfImages[position]
        Picasso.get()
            .load(currentImage.largeImageURL)
            .into(holder.binding.image, object :Callback {
                override fun onSuccess() {
                    holder.binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.binding.progressBar.visibility = View.GONE
                    holder.binding.image.setBackgroundResource(R.drawable.error)
                }
            })

        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(currentImage)
        }
    }

    fun setNewList(newList: List<Image>) {
        val diffCallback = ImageListDiffCallBack(listOfImages, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        diffResult.dispatchUpdatesTo(this)
        listOfImages = newList
    }
}