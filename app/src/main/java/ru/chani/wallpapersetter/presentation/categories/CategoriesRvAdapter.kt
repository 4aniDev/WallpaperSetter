package ru.chani.wallpapersetter.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.chani.wallpapersetter.databinding.CategoryItemBinding
import ru.chani.wallpapersetter.domain.entity.Category

class CategoriesRvAdapter : RecyclerView.Adapter<CategoriesRvAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var onItemClickListener: ((Category) -> Unit)? = null

    private var listOfCategory = listOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfCategory.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.tvCategoryTitle.text = listOfCategory[position].name
        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(listOfCategory[position])
        }
    }

    fun setListOfCategories(newList: List<Category>) {
        listOfCategory = newList
    }
}