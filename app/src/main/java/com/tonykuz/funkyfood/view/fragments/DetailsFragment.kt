package com.tonykuz.funkyfood.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tonykuz.funkyfood.R
import com.tonykuz.funkyfood.databinding.FragmentDetailsBinding
import com.tonykuz.funkyfood.domain.Recipe

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var recipe: Recipe



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecipesDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (!recipe.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_24)
                recipe.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_border_24)
                recipe.isInFavorites = false
            }
        }

        binding.detailsFabShare.setOnClickListener {
            //Создаем интент
            val intent = Intent()
            //Указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашем рецепте
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this recipe: ${recipe.title} \n\n ${recipe.instructions}"
            )
            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }


    private fun setRecipesDetails() {
        //Получаем наш рецепт из переданного бандла
        recipe = arguments?.get("recipe") as Recipe

        //Устанавливаем заголовок
        binding.detailsToolbar.title = recipe.title
        //Устанавливаем картинку
        binding.detailsImage.setImageResource(recipe.image)
        //Устанавливаем инструкции
        binding.detailsInstructions.text = recipe.instructions

        binding.detailsFabFavorites.setImageResource(
            if (recipe.isInFavorites) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite_border_24
        )
    }
}