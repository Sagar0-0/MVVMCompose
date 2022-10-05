package com.example.android.networkcall.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.networkcall.presentation.ui.recipe_list.FoodCategory
import com.example.android.networkcall.presentation.ui.recipe_list.getAllFoodCategories

@Composable
fun SearchAppBar(
    query: String,
    focusManager: FocusManager,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.White,
        elevation = 8.dp,
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        focusManager.clearFocus()
                        onExecuteSearch()
                    }),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    label = {
                        Text("Search")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 8.dp)
            ) {
                itemsIndexed(items = categories) { index, category ->
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                        },
                        onExecuteSearch = onExecuteSearch,
                    )
                }
            }
        }
    }
}