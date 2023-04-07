package com.billysoft.cookingrecipeapp.ui.recipes

import androidx.lifecycle.*
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.usecases.RecipeUseCases
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

    var getRecipesJob: Job? = null
    private val firstLoadReached = AtomicBoolean(false)

    init {
        getRecipes(String())
    }

    fun onSearchTextChanged(inputText: String) {
        getRecipes(inputText)
    }

    private fun getRecipes(filterText: String) {
        getRecipesJob?.cancel()
        getRecipesJob = recipeUseCases.getRecipes(filterText)
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