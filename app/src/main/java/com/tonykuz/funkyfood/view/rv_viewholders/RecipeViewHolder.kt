package com.tonykuz.funkyfood.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tonykuz.funkyfood.R
import com.tonykuz.funkyfood.data.ApiConstants
import com.tonykuz.funkyfood.databinding.RecipeItemBinding
import com.tonykuz.funkyfood.domain.Recipe
import com.tonykuz.funkyfood.view.rv_adapters.RecipeListRecyclerAdapter

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val recipeBinding = RecipeItemBinding.bind(itemView)

    // В этом методе кладем данные из Recipe в наши View
    fun bind(recipe: Recipe, clickListener: RecipeListRecyclerAdapter.OnItemClickListener) {
        // Устанавливаем заголовок
        recipeBinding.title.text = recipe.title

        // Устанавливаем постер
        Glide.with(itemView)
            .load(itemView.context.getString(R.string.image_url, ApiConstants.IMAGES_URL, recipe.id, recipe.image))
            .centerCrop()
            .into(recipeBinding.image)

        // Устанавливаем инструкции
        recipeBinding.instructions.text = recipe.instructions

        // Обрабатываем нажатие на элемент
        recipeBinding.itemContainer.setOnClickListener {
            clickListener.click(recipe)
        }
    }
}