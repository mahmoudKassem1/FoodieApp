package com.example.foodie

import androidx.compose.ui.Modifier
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument // Import navArgument
import androidx.navigation.NavType // Import NavType
import com.example.foodie.screens.*
import com.example.foodie.ui.theme.FoodieTheme
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodieTheme {
                FoodieApp()
            }
        }
    }
}


@Composable
fun FoodieApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("registration") { RegistrationScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("explore_categories") { ExploreCategoriesScreen(navController) }

        composable(
            route = "restaurant_list/{categoryName}"
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            RestaurantScreen(
                navController = navController,
                categoryName = categoryName
            )
        }

        // 🌟 THIS IS THE FIXED BLOCK 🌟
        composable(
            route = "order/{restaurantId}", // 1. Defines the route with parameter
            arguments = listOf(
                navArgument("restaurantId") { type = NavType.StringType } // 2. Defines the argument type
            )
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId")
            OrderScreen( // 3. Passes the parameter to the screen
                navController = navController,
                restaurantId = restaurantId ?: "Unknown Restaurant"
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //... (Keep or remove, as you wish)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //... (Keep or remove, as you wish)
}