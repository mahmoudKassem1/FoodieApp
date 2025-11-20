package com.example.foodie.screens

import android.util.Patterns // For email validation
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
fun RegistrationScreen(navController: NavController) {
    // --- State for Text Fields ---
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // --- State for Validation Errors ---
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    // --- 1. Theme Colors & Gradient (Same as Login) ---
    val primaryColor = Color(0xFF4CAF50)
    val backgroundColorStart = Color(0xFFF1F8E9)
    val backgroundColorEnd = Color(0xFFFFFFFF)
    val darkTextColor = Color(0xFF333333)
    val lightTextColor = Color.Gray

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(backgroundColorStart, backgroundColorEnd)
    )

    // --- 2. Main Layout with Gradient ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- 3. Clean UI with a Card ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
                    text = "Create Account",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkTextColor
                )
                Text(
                    text = "Join the Foodie community",
                    fontSize = 16.sp,
                    color = lightTextColor
                )
                Spacer(modifier = Modifier.height(24.dp))

                // --- Email Field (Styled) ---
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = "" // Clear error on change
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
                    colors = TextFieldDefaults.colors(
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
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = primaryColor,
                        focusedLabelColor = primaryColor,
                        cursorColor = primaryColor
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // --- Confirm Password Field (Styled) ---
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        confirmPasswordError = ""
                    },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = confirmPasswordError.isNotEmpty(),
                    supportingText = {
                        if (confirmPasswordError.isNotEmpty()) {
                            Text(text = confirmPasswordError)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = primaryColor,
                        focusedLabelColor = primaryColor,
                        cursorColor = primaryColor
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                // --- 4. Styled Register Button ---
                Button(
                    onClick = {
                        // --- Run Validation Logic ---
                        var isValid = true

                        // 1. Validate Email
                        if (email.isBlank()) {
                            emailError = "Email cannot be empty"
                            isValid = false
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "Please enter a valid email format"
                            isValid = false
                        }

                        // 2. Validate Password
                        if (password.isBlank()) {
                            passwordError = "Password cannot be empty"
                            isValid = false
                        } else if (password.length < 6) {
                            passwordError = "Password must be at least 6 characters"
                            isValid = false
                        }

                        // 3. Validate Confirm Password
                        if (confirmPassword.isBlank()) {
                            confirmPasswordError = "Please confirm your password"
                            isValid = false
                        } else if (password != confirmPassword) {
                            confirmPasswordError = "Passwords do not match"
                            isValid = false
                        }

                        // 4. Navigate only if all checks pass
                        if (isValid) {
                            // On success, navigate to the login screen
                            // so the user can sign in.
                            navController.navigate("login")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Register", fontSize = 16.sp)
                }
            }
        } // End of Card

        Spacer(modifier = Modifier.height(24.dp))

        // --- 5. Styled Login Button (Secondary Action) ---
        TextButton(onClick = { navController.navigate("login") }) {
            Text(
                text = "Already have an account? Login",
                color = primaryColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}