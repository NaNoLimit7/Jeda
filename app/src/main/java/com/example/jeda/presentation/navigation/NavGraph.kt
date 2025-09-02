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
import com.example.jeda.presentation.onboarding.onBoarding1
import com.example.jeda.presentation.onboarding.onBoarding2
import com.example.jeda.presentation.onboarding.onBoarding3
import com.example.jeda.presentation.onboarding.onBoarding4
import com.example.jeda.presentation.onboarding.onBoardingHub

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
        composable("onBoardingHub") {
            onBoardingHub(
                navController
            )
        }
        composable("onBoarding1") {
            onBoarding1(
                navController, authViewModel
            )
        }
        composable("onBoarding2") {
            onBoarding2(
                navController
            )
        }
        composable("onBoarding3") {
            onBoarding3(
                navController
            )
        }
        composable("onBoarding4") {
            onBoarding4(
                navController
            )
        }
    }
}