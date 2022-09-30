package com.example.android.networkcall.presentation.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.example.android.networkcall.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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


}