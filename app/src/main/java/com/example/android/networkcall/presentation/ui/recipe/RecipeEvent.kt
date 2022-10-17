package com.example.android.networkcall.presentation.ui.recipe

import com.example.android.networkcall.presentation.ui.recipe_list.RecipeListEvent

sealed class RecipeEvent{
    data class GetRecipeEvent(
        val id:Int
    ):RecipeEvent()
}
