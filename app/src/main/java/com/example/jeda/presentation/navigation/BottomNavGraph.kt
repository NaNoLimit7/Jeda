package com.example.jeda.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.presentation.data.BottomBar
import com.example.jeda.presentation.home.BotScreen
import com.example.jeda.presentation.home.HomeScreen
import com.example.jeda.presentation.home.JournalScreen
import com.example.jeda.presentation.home.UserScreen
import com.example.jeda.presentation.registration.LoginScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route
    ){
        composable(route = BottomBar.Home.route){
            HomeScreen( navController, authViewModel)
        }
        composable(route = BottomBar.Bot.route){
            BotScreen( navController)
        }
        composable(route = BottomBar.Journal.route){
            JournalScreen( navController)
        }
        composable(route = BottomBar.User.route){
            UserScreen( navController)
        }
    }
}