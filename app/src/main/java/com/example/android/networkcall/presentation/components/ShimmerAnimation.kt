package com.example.android.networkcall.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val shimmerColorShades= listOf(

    Color.LightGray.copy(0.9f),

    Color.LightGray.copy(0.2f),

    Color.LightGray.copy(0.9f)

)

@Composable
fun ShimmerAnimation(
    cardHeight: Dp,
    cardWidth:Dp,//pass your card height so that shimmer can go all the way down to end of your card
    gradientWidth: Float=200f//pass your shimmer gradient width
) {
    val transition = rememberInfiniteTransition()

    val pxValueX = LocalDensity.current.run {
        cardWidth.toPx()//convert dp value to pixels for x axis
    }
    val pxValueY = LocalDensity.current.run {
        cardHeight.toPx()//convert dp value to pixels for y axis
    }

    val translateX by transition.animateFloat(
        initialValue = 0f,//starts from 0
        targetValue = pxValueX + gradientWidth,//goes all the way down to max pixels value
        animationSpec = infiniteRepeatable(//infinitely repeat the animation
            /*
             Tween Animates between values over specified [durationMillis]
            */
            animation = tween(
                durationMillis = 600,
                easing = LinearEasing,
                delayMillis = 300
            )
        )
    )
    val translateY by transition.animateFloat(
        initialValue = 0f,//starts from 0
        targetValue = pxValueY + gradientWidth,//goes all the way down to max pixels value
        animationSpec = infiniteRepeatable(//infinitely repeat the animation
            /*
             Tween Animates between values over specified [durationMillis]
            */
            animation = tween(
                durationMillis = 600,
                easing = LinearEasing,
                delayMillis = 300,
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end= Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        //start shimmer line
        start = Offset(
            translateX - gradientWidth,
            translateY - gradientWidth
        ),//start even before 0 so that end shimmer line can start from 0
        //end shimmer line
        end = Offset(translateX, translateY)
    )

    //shimmer sample item to show transition
    LazyColumn {
        items(5) {
            ShimmerItem(
                brush = brush,
                height = cardHeight
            )
        }
    }


}


@Composable
fun ShimmerItem(
    brush: Brush,
    height: Dp
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .background(brush = brush)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height/7)
                    .background(brush = brush)
            )
        }
    }

}