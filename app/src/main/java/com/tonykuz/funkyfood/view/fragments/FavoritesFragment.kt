package com.tonykuz.funkyfood.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tonykuz.funkyfood.view.rv_adapters.RecipeListRecyclerAdapter
import com.tonykuz.funkyfood.view.MainActivity
import com.tonykuz.funkyfood.view.rv_adapters.TopSpacingItemDecoration
import com.tonykuz.funkyfood.databinding.FragmentFavoritesBinding
import com.tonykuz.funkyfood.domain.Recipe
import com.tonykuz.funkyfood.utils.AnimationHelper

class FavoritesFragment : Fragment() {

    private lateinit var recipesAdapter: RecipeListRecyclerAdapter
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем список при транзакции фрагмента
        val favoritesList: List<Recipe> = emptyList()

        AnimationHelper.performFragmentCircularRevealAnimation(binding.favoritesFragmentRoot, requireActivity(),2)

        binding.favoritesRecycler.apply {
            recipesAdapter =
                RecipeListRecyclerAdapter(object : RecipeListRecyclerAdapter.OnItemClickListener {
                    override fun click(recipe: Recipe) {
                        (requireActivity() as MainActivity).launchDetailsFragment(recipe)
                    }
                })
            //Присваиваем адаптер
            adapter = recipesAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        recipesAdapter.addItems(favoritesList)

    }
}