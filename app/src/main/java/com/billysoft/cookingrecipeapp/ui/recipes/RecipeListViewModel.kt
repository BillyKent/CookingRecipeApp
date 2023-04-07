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
import kotlinx.coroutines.flow.onStart
import java.util.concurrent.atomic.AtomicBoolean
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
    private val firstLoadReached = AtomicBoolean(false)

    var currentFilter = RecipeFilter.DEFAULT.copy()

    init {
        getRecipes(currentFilter)
    }

    fun onFilterEvent(event: RecipeListEvent) {
        when (event) {
            RecipeListEvent.ClearIngredientsFilter -> {
                currentFilter = currentFilter.copy(
                    ingredientFilter = IngredientFilter.NoFilter
                )
            }
            RecipeListEvent.ClearTextQuery -> {
                currentFilter = currentFilter.copy(
                    queryFilter = QueryFilter.NoFilter
                )
            }
            is RecipeListEvent.SetIngredientsFilter -> {
                currentFilter = currentFilter.copy(
                    ingredientFilter = IngredientFilter.BySelectedIngredients(event.ingredients)
                )
            }
            is RecipeListEvent.SetKeywordFilter -> {
                currentFilter = currentFilter.copy(
                    queryFilter = QueryFilter.ByKeyword(event.keyword)
                )
            }
        }
        getRecipes(currentFilter)
    }

    private fun getRecipes(recipeFilter: RecipeFilter) {
        getRecipesJob?.cancel()
        getRecipesJob = recipeUseCases.getRecipes(recipeFilter)
            .onStart {
                if (!firstLoadReached.get()) {
                    _uiEvent.value = UiEvent.ShowLoading
                }
            }
            .onEach { recipes ->
                _recipeList.value = recipes
                if (!firstLoadReached.getAndSet(true)) {
                    _uiEvent.value = UiEvent.HideLoading
                }
            }
            .launchIn(viewModelScope)
    }

    sealed class UiEvent {
        object ShowLoading : UiEvent()
        object HideLoading : UiEvent()
    }
}