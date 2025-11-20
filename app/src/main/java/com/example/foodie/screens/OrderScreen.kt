package com.example.foodie.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random // Import for generating random prices
import androidx.compose.runtime.remember // <--- 🌟 FIX ADDED HERE 🌟
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api // <-- 🌟 NEW REQUIRED IMPORT 🌟

// Data class to represent an item in the cart
data class CartItem(
    val name: String,
    val quantity: Int,
    val price: Double
)

@OptIn(ExperimentalMaterial3Api::class) // <-- 🌟 FIX: ADDED ANNOTATION 🌟
@Composable
fun OrderScreen(navController: NavController, restaurantId: String) {

    // --- Theme Variables ---
    val primaryColor = Color(0xFF4CAF50) // Green
    val darkTextColor = Color(0xFF333333)
    val lightTextColor = Color.Gray
    val backgroundColorStart = Color(0xFFF1F8E9)
    val backgroundColorEnd = Color(0xFFFFFFFF)
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )

    // --- Dummy Cart Data ---
    val dummyOrders = remember {
        listOf(
            CartItem("Classic Burger", 1, 12.99),
            CartItem("Pepperoni Pizza", 2, 9.50),
            CartItem("Wonton Soup", 1, 5.00),
            CartItem("Chicken Shawarma", 1, 10.75)
        )
    }

    // Calculate the total price
    val subtotal = dummyOrders.sumOf { it.quantity * it.price }
    // Randomly generate shipping for prototype realism
    val shipping = (Random.nextDouble(1.0, 5.0) * 10).toInt() / 10.0
    val total = subtotal + shipping

    // --- Screen Layout ---
    Scaffold(
        topBar = {
            TopAppBar( // No longer causes an error thanks to @OptIn
                title = { Text("Your Cart") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Restaurant"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        // We put the content inside a Box to apply the gradient background
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBrush)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // --- Bag Icon and Header ---
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        tint = primaryColor,
                        modifier = Modifier.size(48.dp).padding(top = 8.dp)
                    )
                    Text(
                        text = "Orders from Restaurant: $restaurantId", // Using the passed parameter
                        style = MaterialTheme.typography.titleMedium,
                        color = darkTextColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // --- List of Orders (The Cart) ---
                    CartItemList(dummyOrders)

                    Spacer(modifier = Modifier.height(16.dp))

                    // --- Price Summary ---
                    PriceSummary(subtotal, shipping, total, darkTextColor, lightTextColor)

                    // Use weight(1f) to push the final button to the bottom
                    Spacer(modifier = Modifier.weight(1f))

                    // --- Checkout Button ---
                    Button(
                        onClick = { /* Placeholder for Checkout Logic */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Proceed to Checkout", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    )
}

@Composable
fun CartItemList(items: List<CartItem>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            userScrollEnabled = false // Disable scrolling for this small list
        ) {
            items(items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.name, fontWeight = FontWeight.SemiBold, color = Color(0xFF333333))
                        Text(text = "Qty: ${item.quantity}", fontSize = 12.sp, color = Color.Gray)
                    }
                    Text(text = "$${"%.2f".format(item.price * item.quantity)}", fontWeight = FontWeight.Bold)
                }
                Divider(color = Color(0xFFE0E0E0)) // Light gray divider
            }
        }
    }
}

@Composable
fun PriceSummary(subtotal: Double, shipping: Double, total: Double, darkTextColor: Color, lightTextColor: Color) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.End
    ) {
        // Subtotal
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Subtotal:", color = lightTextColor)
            Text(text = "$${"%.2f".format(subtotal)}", color = darkTextColor)
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Shipping
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Shipping:", color = lightTextColor)
            Text(text = "$${"%.2f".format(shipping)}", color = darkTextColor)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Total
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Total:", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = darkTextColor)
            Text(text = "$${"%.2f".format(total)}", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = Color(0xFFB71C1C)) // Use a highlight color for the final price
        }
    }
}