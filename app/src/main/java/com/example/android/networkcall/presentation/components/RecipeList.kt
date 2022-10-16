package com.example.android.networkcall.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.android.networkcall.prese.PAGE_SIZE
import com.example.android.networkcall.prese.RecipeListViewModel
import com.example.android.networkcall.presentation.ui.recipe_list.RecipeListEvent
import com.example.android.networkcall.presentation.ui.recipe_list.RecipeListFragmentDirections
import kotlinx.coroutines.launch

@Composable
fun RecipeList(
    viewModel: RecipeListViewModel,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val loading = viewModel.loading.value
    val recipes = viewModel.recipes.value
    val page = viewModel.page.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        if (loading && recipes.isEmpty()) {
            ShimmerAnimation(
                cardHeight = 225.dp,
                cardWidth = LocalConfiguration.current.screenWidthDp.dp
            )
        } else {
            LazyColumn {
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    viewModel.onChangeRecipeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent)
                    }
                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            if (recipe.id != null) {
                                val action=RecipeListFragmentDirections.actionRecipeListFragmentToRecipeFragment(recipe.id)
                                navController.navigate(action)
                            } else {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        actionLabel = "Ok",
                                        message = "Recipe ERROR"
                                    )
                                }

                            }
                        }
                    )
                }
            }
        }
        DefaultSnackbar(
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
}