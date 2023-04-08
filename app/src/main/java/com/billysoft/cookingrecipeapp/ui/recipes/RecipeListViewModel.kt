package com.billysoft.cookingrecipeapp.ui.recipes

import androidx.lifecycle.*
import com.billysoft.cookingrecipeapp.R
import com.billysoft.cookingrecipeapp.util.DrawableMapper
import com.billysoft.domain.model.Recipe
import com.billysoft.domain.model.exceptions.ExceptionCause
import com.billysoft.domain.usecases.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
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

                recipes.flatMap { it.ingredients }.distinct()
                    .filter { DrawableMapper().getIngredientDrawable(it) == R.drawable.ic_vegetable }.forEach {
                        println(it)
                    }

                if (recipes.isNotEmpty()) {
                    if (!firstLoadReached.getAndSet(true)) {
                        _uiEvent.value = UiEvent.HideLoading
                    }
                }
            }
            .catch { exception ->
                val cause = ExceptionCause.fromException(exception)
                if (cause != ExceptionCause.API_ERROR) {
                    delay(800)
                }
                _uiEvent.value = UiEvent.ErrorLoading(cause)
            }
            .launchIn(viewModelScope)
    }

    sealed class UiEvent {
        object ShowLoading : UiEvent()
        object HideLoading : UiEvent()
        data class ErrorLoading(val cause: ExceptionCause) : UiEvent()

    }
}