package com.example.foodie.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ExploreCategoriesScreen(navController: NavController) {

    // --- Theme (Matches Login/Registration/Home Gradient) ---
    val primaryColor = Color(0xFF4CAF50) // Green
    val darkTextColor = Color(0xFF333333)
    val backgroundColorStart = Color(0xFFF1F8E9) // Light Green/Cream
    val backgroundColorEnd = Color(0xFFFFFFFF) // White

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush), // Apply the professional gradient background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f) // Use 85% of the screen width
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // --- Title ---
            Text(
                text = "Ready to Find Your Next Meal?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // --- Subtitle/Description ---
            Text(
                text = "Tap below to explore fresh culinary categories and start ordering.",
                fontSize = 16.sp,
                color = darkTextColor.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // --- Navigation Button ---
            Button(
                onClick = {
                    // Navigate to the HomeScreen
                    navController.navigate("home") {
                        // Prevent going back to this screen from 'home'
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Explore Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}