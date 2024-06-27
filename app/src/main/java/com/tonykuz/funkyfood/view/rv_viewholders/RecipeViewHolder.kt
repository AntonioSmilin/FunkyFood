package com.tonykuz.funkyfood.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tonykuz.funkyfood.data.ApiConstants
import com.tonykuz.funkyfood.databinding.RecipeItemBinding
import com.tonykuz.funkyfood.domain.Recipe

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val recipeBinding = RecipeItemBinding.bind(itemView)

    //В этом методе кладем данные из Recipe в наши View
    fun bind(recipe: Recipe) {


        //Устанавливаем заголовок
        recipeBinding.title.text = recipe.title
        //Устанавливаем постер
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(ApiConstants.IMAGES_URL + recipe.id + "-312x231" + recipe.image)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(recipeBinding.image)
            //Устанавливаем инструкции
            recipeBinding.instructions.text = recipe.instructions
    }
}