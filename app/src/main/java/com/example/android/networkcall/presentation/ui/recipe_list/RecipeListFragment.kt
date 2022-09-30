package com.example.android.networkcall.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.android.networkcall.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    val viewModel : RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = ComposeView(requireContext()).apply {
            setContent {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "RecipeList",
                        style = TextStyle(
                            fontSize = 21.sp
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {
                            findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment)
                        }
                    ) {
                        Text(text = "TO RECIPE FRAGMENT")
                    }
                }
            }
        }

        return view
    }

}