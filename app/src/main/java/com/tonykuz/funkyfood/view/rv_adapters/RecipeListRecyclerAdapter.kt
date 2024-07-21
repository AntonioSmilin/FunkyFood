package com.tonykuz.funkyfood.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tonykuz.funkyfood.view.rv_viewholders.RecipeViewHolder
import com.tonykuz.funkyfood.R
import com.tonykuz.funkyfood.domain.Recipe

class RecipeListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    // Список элементов для RecyclerView
    private val items = mutableListOf<Recipe>()

    // Возврат количества элементов в списке
    override fun getItemCount() = items.size

    // Привязываем ViewHolder и "надуваем" верстку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    // Добавление объектов в список
    fun addItems(list: List<Recipe>) {
        val diffCallback = RecipeDiffCallback(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    // Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(recipe: Recipe)
    }
}