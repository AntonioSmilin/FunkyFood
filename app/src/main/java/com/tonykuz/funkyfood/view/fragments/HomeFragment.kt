package com.tonykuz.funkyfood.view.fragments

import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tonykuz.funkyfood.view.rv_adapters.RecipeListRecyclerAdapter
import com.tonykuz.funkyfood.view.MainActivity
import com.tonykuz.funkyfood.R
import com.tonykuz.funkyfood.view.rv_adapters.TopSpacingItemDecoration
import com.tonykuz.funkyfood.databinding.FragmentHomeBinding
import com.tonykuz.funkyfood.databinding.MergeHomeScreenContentBinding
import com.tonykuz.funkyfood.domain.Recipe
import com.tonykuz.funkyfood.utils.AnimationHelper
import com.tonykuz.funkyfood.viewmodel.HomeFragmentViewModel
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var recipesAdapter: RecipeListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var binding2: MergeHomeScreenContentBinding
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    private var recipesDataBases = listOf<Recipe>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            recipesAdapter.addItems(field)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding2 =
            MergeHomeScreenContentBinding.inflate(layoutInflater, binding.homeFragmentRoot, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.recipesListLiveData.observe(viewLifecycleOwner, Observer<List<Recipe>> {
            recipesDataBases = it
        })
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)

        val scene = Scene(binding.homeFragmentRoot, binding2.root)
        // search view animation
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        // RV animation
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(R.id.main_recycler)
        val customTransition = TransitionSet().apply {
            duration = 500
            addTransition(recyclerSlide)
            addTransition(searchSlide)
        }

        TransitionManager.go(scene, customTransition)

        binding2.searchView.setOnClickListener {
            binding2.searchView.isIconified = false
        }


        binding2.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    recipesAdapter.addItems(recipesDataBases)
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                val result = recipesDataBases.filter {
                    //Чтобы все работало правильно, нужно и запрос, и название рецепта приводить к нижнему регистру
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }

                //Добавляем в адаптер
                recipesAdapter.addItems(result)
                return true
            }
        })

        initRecycler()
        recipesAdapter.addItems(recipesDataBases)
    }

    private fun initRecycler() {
        // get RV
        binding2.mainRecycler.apply {
            recipesAdapter =
                RecipeListRecyclerAdapter(object : RecipeListRecyclerAdapter.OnItemClickListener {
                    override fun click(recipe: Recipe) {
                        (requireActivity() as MainActivity).launchDetailsFragment(recipe)
                    }
                })

            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

}