package com.example.android.networkcall.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * Center a circular indeterminate progress bar with optional vertical bias.
 */
@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean, verticalBias: Float) {
    if(isDisplayed){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ){
            val (progressBar) = createRefs()
            val topBias = createGuidelineFromTop(verticalBias)
            CircularProgressIndicator(
                modifier = Modifier
                    .constrainAs(progressBar) {
                        top.linkTo(topBias)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                color = MaterialTheme.colors.primary
            )
        }

    }
}
