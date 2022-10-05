package com.example.android.networkcall.prese

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.networkcall.domain.model.Recipe
import com.example.android.networkcall.presentation.ui.recipe_list.FoodCategory
import com.example.android.networkcall.presentation.ui.recipe_list.getFoodCategory
import com.example.android.networkcall.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository,
    @Named("auth_token")
    private val token: String
) : ViewModel() {
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    val loading = mutableStateOf(false)
    var previousSearch = "xyz"

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            if(previousSearch.trim().lowercase()==query.value.trim().lowercase()) return@launch
            previousSearch=query.value
            loading.value = true
            resetSearchState()
            val result = recipeRepository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result
            loading.value = false
        }
    }

    fun onQueryChanged(newValue: String) {
        query.value = newValue
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        if (selectedCategory.value?.value?.lowercase() != query.value.lowercase()) {
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }
}