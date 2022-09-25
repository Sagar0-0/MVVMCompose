package com.example.android.networkcall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkCallTheme()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkCallTheme() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF1F1F1))
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.sagar0_0dsa__1_),
            contentDescription = "SAGAR0-0 DSA",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp, 300.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .background(Color(0xF2F2F2F2))
                .padding(20.dp)
                .fillMaxSize(),

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sagar0-0",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Text(
                    text = "150+ Stars",
                    fontStyle = FontStyle.Italic,
                    color = Color.Yellow,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(text = "BUILDING THE LARGEST DSA SOLUTION REPOSITORY TOGETHER")
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Yellow,
                    contentColor = Color.Magenta
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "YOUR BUTTON")
            }
        }
    }
}
