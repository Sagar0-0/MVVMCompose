package com.example.android.networkcall.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.android.networkcall.prese.RecipeListViewModel
import com.example.android.networkcall.presentation.BaseApplication
import com.example.android.networkcall.presentation.components.RecipeList
import com.example.android.networkcall.presentation.components.SearchAppBar
import com.example.android.networkcall.presentation.theme.AppTheme
import com.example.android.networkcall.presentation.ui.recipe_list.RecipeListEvent.NewSearchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(darkTheme = application.isDark.value) {
                    val query = viewModel.query.value
                    val focusManager: FocusManager = LocalFocusManager.current
                    val selectedCategory = viewModel.selectedCategory.value

                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = { scaffoldState.snackbarHostState },
                        topBar = {
                            SearchAppBar(
                                query = query,
                                focusManager = focusManager,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {
                                    if (viewModel.selectedCategory.value?.value == "Milk") {
                                        lifecycleScope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                message = "No data Exists for Milk",
                                                actionLabel = "DISMISS"
                                            )
                                        }
                                    } else {
                                        viewModel.onTriggerEvent(NewSearchEvent)
                                    }
                                },
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onToggleTheme = application::toggleTheme

                            )
                        }
                    ) {
                        RecipeList(
                            viewModel = viewModel, scaffoldState = scaffoldState,
                            navController = findNavController()
                        )
                    }
                }
            }
        }
    }
}
