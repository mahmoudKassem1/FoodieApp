package com.example.foodie.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.foodie.R.drawable.unnamed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {


    val backgroundColorStart = Color(0xFFF1F8E9)
    val backgroundColorEnd = Color(0xFFFFFFFF)
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )


    LaunchedEffect(Unit) {
        delay(3000) // Keep the 3-second delay


        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {

        Image(

            painter = painterResource(id = unnamed),
            contentDescription = "Foodie App Logo",
            modifier = Modifier.size(500.dp)
        )
    }
}