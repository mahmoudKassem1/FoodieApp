package com.example.foodie.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.* // Import Material 3 components
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // --- 1. Theme Colors & Gradient ---
    // A fresh, "organic" restaurant theme
    val primaryColor = Color(0xFF4CAF50) // A nice, appetizing green
    val backgroundColorStart = Color(0xFFF1F8E9) // Very light green
    val backgroundColorEnd = Color(0xFFFFFFFF) // Fades to white
    val darkTextColor = Color(0xFF333333) // Dark gray, softer than black
    val lightTextColor = Color.Gray

    // Define the gradient brush
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )

    // --- 2. Main Layout with Gradient ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush) // Apply the gradient
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- 3. Clean UI with a Card ---
        // We place the form inside a Card for a clean, floating look
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Add horizontal padding
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // --- Header Text ---
                Text(
                    text = "Welcome Back!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkTextColor
                )
                Text(
                    text = "Sign in to your Foodie account",
                    fontSize = 16.sp,
                    color = lightTextColor
                )
                Spacer(modifier = Modifier.height(24.dp))

                // --- Email Field (Styled) ---
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = ""
                    },
                    label = { Text("Email") },
                    isError = emailError.isNotEmpty(),
                    supportingText = {
                        if (emailError.isNotEmpty()) {
                            Text(text = emailError)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors( // Match theme
                        focusedIndicatorColor = primaryColor,
                        focusedLabelColor = primaryColor,
                        cursorColor = primaryColor
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- Password Field (Styled) ---
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = ""
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = passwordError.isNotEmpty(),
                    supportingText = {
                        if (passwordError.isNotEmpty()) {
                            Text(text = passwordError)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors( // Match theme
                        focusedIndicatorColor = primaryColor,
                        focusedLabelColor = primaryColor,
                        cursorColor = primaryColor
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                // --- 4. Styled Login Button ---
                Button(
                    onClick = {
                        // --- Validation Logic (Unchanged) ---
                        var isValid = true
                        if (email.isBlank()) {
                            emailError = "Email cannot be empty"
                            isValid = false
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "Please enter a valid email format"
                            isValid = false
                        }
                        if (password.isBlank()) {
                            passwordError = "Password cannot be empty"
                            isValid = false
                        }
                        if (isValid) {
                            navController.navigate("explore_categories")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Login", fontSize = 16.sp)
                }
            }
        } // End of Card

        Spacer(modifier = Modifier.height(24.dp))

        // --- 5. Styled Register Button (Secondary Action) ---
        // Using a TextButton for the secondary action is cleaner
        TextButton(onClick = { navController.navigate("registration") }) {
            Text(
                text = "Don't have an account? Register",
                color = primaryColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}