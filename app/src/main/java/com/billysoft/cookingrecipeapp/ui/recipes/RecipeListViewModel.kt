package com.billysoft.cookingrecipeapp.ui.recipes

import androidx.lifecycle.*
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.usecases.RecipeUseCases
import com.billysoft.domain.util.IngredientFilter
import com.billysoft.domain.util.QueryFilter
import com.billysoft.domain.util.RecipeFilter
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

    private val _uiEvent = MutableLiveData<UiEvent>()
    val uiEvent: LiveData<UiEvent> get() = _uiEvent

    val resultsCount = recipeList.map { it.size }

    private var getRecipesJob: Job? = null

    init {
        getRecipes(RecipeFilter.DEFAULT)
    }

    fun onFilterEvent(event: RecipeListEvent) {
        val filter = RecipeFilter.DEFAULT
        when (event) {
            RecipeListEvent.ClearIngredientsFilter -> {
                filter.ingredientFilter = IngredientFilter.NoFilter
            }
            RecipeListEvent.ClearTextQuery -> {
                filter.queryFilter = QueryFilter.NoFilter
            }
            is RecipeListEvent.SetIngredientsFilter -> {
                filter.ingredientFilter = IngredientFilter.BySelectedIngredients(event.ingredients)
            }
            is RecipeListEvent.SetKeywordFilter -> {
                filter.queryFilter = QueryFilter.ByKeyword(event.keyword)
            }
        }
        getRecipes(filter)
    }

    private fun getRecipes(recipeFilter: RecipeFilter) {
        getRecipesJob?.cancel()
        getRecipesJob = recipeUseCases.getRecipes(recipeFilter)
            .onEach { recipes ->
                _recipeList.value = recipes
            }
            .launchIn(viewModelScope)
    }

    sealed class UiEvent {
        object ShowLoading : UiEvent()
        object HideLoading : UiEvent()
    }
}