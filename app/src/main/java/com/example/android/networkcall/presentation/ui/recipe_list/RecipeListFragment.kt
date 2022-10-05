package com.example.android.networkcall.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.networkcall.prese.RecipeListViewModel
import com.example.android.networkcall.presentation.components.CircularIndeterminateProgressBar
import com.example.android.networkcall.presentation.components.FoodCategoryChip
import com.example.android.networkcall.presentation.components.RecipeCard
import com.example.android.networkcall.presentation.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val focusManager:FocusManager = LocalFocusManager.current
                val selectedCategory=viewModel.selectedCategory.value
                val loading =viewModel.loading.value

                Column {

                    SearchAppBar(
                        query = query,
                        focusManager = focusManager,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        categories = getAllFoodCategories(),
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged
                    )
                    Box(modifier = Modifier.fillMaxSize()){
                        LazyColumn {
                            itemsIndexed(
                                items = recipes
                            ) { index, recipe ->
                                RecipeCard(recipe = recipe, onClick = {})
                            }
                        }

                        CircularIndeterminateProgressBar(isDisplayed = loading,0.3f)

                    }

                }
            }
        }
    }

}

