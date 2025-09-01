package com.example.jeda.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.presentation.registration.LoginScreen
import com.example.jeda.presentation.registration.SignUpScreen
import com.example.jeda.presentation.SplashScreen
import com.example.jeda.presentation.home.HomeScreen

@Composable
fun NavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
            SplashScreen(navController)
        }
        composable("LoginScreen") {
            LoginScreen(
                navController, authViewModel
            )
        }
        composable("SignUpScreen") {
            SignUpScreen(
                navController, authViewModel
            )
        }
        composable("HomeScreen") {
            HomeScreen(
                navController, authViewModel
            )
        }
    }

}