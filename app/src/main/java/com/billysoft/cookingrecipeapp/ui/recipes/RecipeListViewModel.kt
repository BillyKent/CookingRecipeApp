package com.billysoft.cookingrecipeapp.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.usecases.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
) : ViewModel() {

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

    private var getRecipesJob: Job? = null

    init {
        getRecipes()
    }

    private fun getRecipes() {
        getRecipesJob?.cancel()
        getRecipesJob = recipeUseCases.getRecipes()
            .onEach { recipes ->
                _recipeList.value = recipes
            }
            .launchIn(viewModelScope)
    }

}