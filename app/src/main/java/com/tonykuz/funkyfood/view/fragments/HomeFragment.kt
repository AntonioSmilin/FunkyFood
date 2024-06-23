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
import androidx.recyclerview.widget.LinearLayoutManager
import com.tonykuz.funkyfood.view.rv_adapters.FilmListRecyclerAdapter
import com.tonykuz.funkyfood.view.MainActivity
import com.tonykuz.funkyfood.R
import com.tonykuz.funkyfood.view.rv_adapters.TopSpacingItemDecoration
import com.tonykuz.funkyfood.databinding.FragmentHomeBinding
import com.tonykuz.funkyfood.databinding.MergeHomeScreenContentBinding
import com.tonykuz.funkyfood.domain.Film
import com.tonykuz.funkyfood.utils.AnimationHelper
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var binding2: MergeHomeScreenContentBinding


    val filmsDataBase = listOf(
        Film(
            "Recipe 1",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 2",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 3",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 4",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 5",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 6",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 7",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 8",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 9",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
        Film(
            "Recipe 10",
            R.drawable.icon,
            "In a large skillet over medium heat, heat the oil and 1 tablespoon of the butter. Saut the onions and garlic until softened. Raise the heat to medium-high and add the clam juice and 1/2 cup wine. Simmer to reduce by half. Add the tomatoes, salt, pepper, chili flakes, lemon juice, shrimp, and remaining wine and simmer for 5 minutes until the shrimp are firm and pink. Add the crab and remaining butter and briefly heat through. Meanwhile, cook the pasta in boiling salted water. Drain and toss with olive oil and basil. TO SERVE In each bowl, place 1/4 of the pasta. Top the pasta with 1/4 of seafood sauce. Dust with the Parmesan and chopped parsley."
        ),
    )

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
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поиск подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }

                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })

        initRecycler()
        filmsAdapter.addItems(filmsDataBase)
    }

    private fun initRecycler() {
        // get RV
        binding2.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })

            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

}