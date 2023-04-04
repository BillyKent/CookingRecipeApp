package com.billysoft.cookingrecipeapp.ui.detail

import androidx.lifecycle.*
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.usecases.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeUseCases: RecipeUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipeDetail = MutableLiveData<Recipe>()
    val recipeDetail: LiveData<Recipe> get() = _recipeDetail

    init {
        savedStateHandle.get<String>("recipe_id")?.let { recipeId ->
            viewModelScope.launch {
                recipeUseCases.getRecipe(recipeId)?.let { recipe ->
                    _recipeDetail.value = recipe
                }
            }
        }

    }

}