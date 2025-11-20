package com.example.foodie.screens

// --- Imports needed for the new layout ---
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodie.R
@Composable
fun HomeScreen(navController: NavController) {
    // --- 1. Theme (Same as Login/Registration) ---
    val primaryColor = Color(0xFF4CAF50)
    val darkTextColor = Color(0xFF333333)
    val backgroundColorStart = Color(0xFFF1F8E9)
    val backgroundColorEnd = Color(0xFFFFFFFF)
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )

    // --- 2. Category List ---
    // Your 4 categories
    val categories = listOf("Burger", "Pizza", "Asian", "Syrian")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(16.dp), // Overall screen padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- 3. Header ---
        Text(
            text = "Find Your Craving",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = darkTextColor
        )
        Spacer(modifier = Modifier.height(24.dp))

        // --- 4. Grid of Categories ---
        // A LazyVerticalGrid is perfect for a 2x2 category layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // We want 2 columns
            contentPadding = PaddingValues(8.dp)
        ) {
            items(categories) { categoryName ->
                // We pass each category name to a reusable Card composable
                CategoryCard(
                    categoryName = categoryName,
                    darkTextColor = darkTextColor,
                    onClick = {
                        navController.navigate("restaurant_list/$categoryName")
                    }
                )
            }
        }
    }
}


@Composable
fun CategoryCard(
    categoryName: String,
    darkTextColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(30.dp) // <-- I INCREASED THIS PADDING
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val imageRes = when (categoryName) {
                "Burger" -> R.drawable.burger
                "Pizza"  -> R.drawable.pizza
                "Asian"  -> R.drawable.asian
                "Syrian" -> R.drawable.syrian
                else     -> R.drawable.ic_launcher_background
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "$categoryName Category Photo",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = categoryName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor
            )
        }
    }
}