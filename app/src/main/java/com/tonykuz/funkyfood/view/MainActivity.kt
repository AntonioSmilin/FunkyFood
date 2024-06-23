package com.tonykuz.funkyfood.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tonykuz.funkyfood.R
import com.tonykuz.funkyfood.databinding.ActivityMainBinding
import com.tonykuz.funkyfood.domain.Recipe
import com.tonykuz.funkyfood.view.fragments.DetailsFragment
import com.tonykuz.funkyfood.view.fragments.FavoritesFragment
import com.tonykuz.funkyfood.view.fragments.HomeFragment
import com.tonykuz.funkyfood.view.fragments.SelectionsFragment
import com.tonykuz.funkyfood.view.fragments.CookLaterFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backPressed = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()

        //Запускаем фрагмент при старте
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    fun launchDetailsFragment(recipe: Recipe) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш рецепт в "посылку"
        bundle.putParcelable("recipe", recipe)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    //элвиса мы вызываем создание нового фрагмента
                    changeFragment( fragment?: HomeFragment(), tag)
                    Toast.makeText(this, getString(R.string.home_title_text), Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.favorites -> {
                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavoritesFragment(), tag)
                    Toast.makeText(this, getString(R.string.favorites_title), Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.cook_later -> {
                    val tag = "cook_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: CookLaterFragment(), tag)
                    Toast.makeText(this, getString(R.string.cook_later_title), Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: SelectionsFragment(), tag)
                    Toast.makeText(this, getString(R.string.selections_title), Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
    //Ищем фрагмент по тэгу, если он есть то возвращаем его, если нет - то null
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.double_back_toast), Toast.LENGTH_SHORT)
                    .show()
            }

            backPressed = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val TIME_INTERVAL = 2000
    }
}