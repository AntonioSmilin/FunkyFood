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
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()

        // Запускаем начальный фрагмент при старте
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, HomeFragment(), "home")
                .commit()
        }
    }

    fun launchDetailsFragment(recipe: Recipe) {
        val bundle = Bundle().apply {
            putParcelable("recipe", recipe)
        }

        val fragment = DetailsFragment().apply {
            arguments = bundle
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            val fragment: Fragment?
            val tag: String
            val toastMessage: String

            when (it.itemId) {
                R.id.home -> {
                    tag = "home"
                    fragment = checkFragmentExistence(tag) ?: HomeFragment()
                    toastMessage = getString(R.string.home_title_text)
                }
                R.id.favorites -> {
                    tag = "favorites"
                    fragment = checkFragmentExistence(tag) ?: FavoritesFragment()
                    toastMessage = getString(R.string.favorites_title)
                }
                R.id.cook_later -> {
                    tag = "cook_later"
                    fragment = checkFragmentExistence(tag) ?: CookLaterFragment()
                    toastMessage = getString(R.string.cook_later_title)
                }
                R.id.selections -> {
                    tag = "selections"
                    fragment = checkFragmentExistence(tag) ?: SelectionsFragment()
                    toastMessage = getString(R.string.selections_title)
                }
                else -> return@setOnItemSelectedListener false
            }

            changeFragment(fragment, tag)
            showToast(toastMessage)
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Ищем фрагмент по тэгу, если он есть, то возвращаем его, если нет - то null
    private fun checkFragmentExistence(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            if (backPressedTime + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                showToast(getString(R.string.double_back_toast))
            }
            backPressedTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TIME_INTERVAL = 2000L
    }
}