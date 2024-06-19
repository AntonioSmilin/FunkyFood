package com.tonykuz.funkyfood

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
import com.tonykuz.funkyfood.databinding.FragmentHomeBinding
import com.tonykuz.funkyfood.databinding.MergeHomeScreenContentBinding
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var binding2: MergeHomeScreenContentBinding


    val filmsDataBase = listOf(
        Film(
            "Список Шиндлера",
            R.drawable.icon,
            "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis."
        ),
        Film(
            "Бойцовский клуб",
            R.drawable.icon,
            "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more."
        ),
        Film(
            "Начало",
            R.drawable.icon,
            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster."
        ),
        Film(
            "Матрица",
            R.drawable.icon,
            "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence."
        ),
        Film(
            "Семь",
            R.drawable.icon,
            "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives."
        ),
        Film(
            "Интерстеллар",
            R.drawable.icon,
            "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans."
        ),
        Film(
            "Зелёная миля",
            R.drawable.icon,
            "A tale set on death row in a Southern jail, where gentle giant John possesses the mysterious power to heal people's ailments. When the lead guard, Paul, recognizes John's gift, he tries to help stave off the condemned man's execution."
        ),
        Film(
            "Терминатор 2: Судный день",
            R.drawable.icon,
            "A cyborg, identical to the one who failed to kill Sarah Connor, must now protect her ten year old son John from an even more advanced and powerful cyborg."
        ),
        Film(
            "Паразиты",
            R.drawable.icon,
            "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan."
        ),
        Film(
            "Король Лев",
            R.drawable.icon,
            "Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself."
        )
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