package com.billysoft.cookingrecipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.billysoft.cookingrecipeapp.R
import com.billysoft.domain.usecases.RecipeUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var useCases: RecipeUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            useCases.getRecipes().collect { recipes ->
                Toast.makeText(this@MainActivity, recipes.joinToString(), Toast.LENGTH_SHORT).show()
            }
        }

    }
}