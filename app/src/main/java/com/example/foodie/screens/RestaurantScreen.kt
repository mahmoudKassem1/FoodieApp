package com.example.foodie.screens

// --- Imports ---
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// --- 1. A Data Class for our Restaurant ---
data class Restaurant(
    val name: String,
    val price: String // e.g., "$", "$$", "$$$"
)

@Composable
fun RestaurantScreen(
    navController: NavController,
    categoryName: String?
) {

    // --- 2. Theme (Same as other screens) ---
    val primaryColor = Color(0xFF4CAF50)
    val darkTextColor = Color(0xFF333333)
    val backgroundColorStart = Color(0xFFF1F8E9)
    val backgroundColorEnd = Color(0xFFFFFFFF)
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )

    // --- 3. Dynamic Restaurant Lists ---
    val restaurantList = when (categoryName) {
        "Burger" -> listOf(
            Restaurant("The Patty Shack", "$$"),
            Restaurant("Gourmet Grill", "$$$"),
            Restaurant("Quick Burger", "$"),
            Restaurant("Burger Barn", "$$")
        )
        "Pizza" -> listOf(
            Restaurant("Nonna's Pizzeria", "$$"),
            Restaurant("Slice & Go", "$"),
            Restaurant("Vesuvio Fine Dining", "$$$"),
            Restaurant("The Pizza Oven", "$$")
        )
        "Asian" -> listOf(
            Restaurant("Wok & Steam", "$$"),
            Restaurant("Tokyo Express", "$"),
            Restaurant("Golden Dragon", "$$$"),
            Restaurant("Bao Down", "$$")
        )
        "Syrian" -> listOf(
            Restaurant("Damascus Kitchen", "$$"),
            Restaurant("Aleppo's Corner", "$"),
            Restaurant("Yasmine Palace", "$$$"),
            Restaurant("Beirut Nights", "$$")
        )
        else -> emptyList() // Default to an empty list
    }

    // --- 4. Main Layout ---
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // --- 5. Header Area ---
            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "$categoryName Restaurants",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- 6. Scrollable List of Restaurants ---
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
            ) {
                items(restaurantList) { restaurant ->
                    RestaurantListItem(
                        restaurant = restaurant,
                        primaryColor = primaryColor,
                        darkTextColor = darkTextColor,
                        onClick = {
                            // 🌟 NAVIGATION FIX 🌟
                            // Navigate to the OrderScreen, passing the restaurant's name
                            // as the ID. The NavHost in MainActivity must be set up to
                            // accept this parameter ("order/{restaurantId}").
                            navController.navigate("order/${restaurant.name}")
                        }
                    )
                }
            }
        }

        // --- 7. Back Button (Unchanged) ---
        IconButton(
            onClick = { navController.popBackStack() }, // Go back
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.7f))
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = darkTextColor
            )
        }
    }
}

/**
 * A reusable Composable for a single restaurant row in the list.
 */
@Composable
fun RestaurantListItem(
    restaurant: Restaurant,
    primaryColor: Color,
    darkTextColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            // 🌟 CLICK HANDLER IS HERE 🌟
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Restaurant Name
            Text(
                text = restaurant.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = darkTextColor
            )

            // This spacer pushes the price to the end
            Spacer(modifier = Modifier.weight(1f))

            // Price Rating
            Text(
                text = restaurant.price,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
        }
    }
}