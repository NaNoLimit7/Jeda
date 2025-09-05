package com.example.jeda.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.gemini.ViewModel.ChatViewModel
import com.example.jeda.presentation.data.BottomBar
import com.example.jeda.presentation.home.BotScreen
import com.example.jeda.presentation.home.HomeScreen
import com.example.jeda.presentation.home.InsertNotesScreen
import com.example.jeda.presentation.home.JournalScreen
import com.example.jeda.presentation.home.MoodScreen
import com.example.jeda.presentation.home.UserScreen
import com.example.jeda.presentation.home.onBoardingBot1
import com.example.jeda.presentation.registration.LoginScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    chatViewModel: ChatViewModel
) {
//    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BottomBar.Home.route
    ){
        composable(route = BottomBar.Home.route){
            HomeScreen( navController)
        }
        composable(route = BottomBar.Bot.route){
            onBoardingBot1( navController)
        }
        composable(route = BottomBar.Journal.route){
            JournalScreen( navController)
        }
        composable("JournalScreen"){
            JournalScreen( navController)
        }
        composable("InsertNotesScreen" + "/{id}"){
            val id = it.arguments?.getString("id")
            InsertNotesScreen(navController, id)
        }
        composable(route = BottomBar.User.route){
            UserScreen(navController, authViewModel)
        }
        composable("onBoardingBot1"){
            onBoardingBot1( navController)
        }
        composable("BotScreen"){
            BotScreen(
                navController,
                chatViewModel
            )
        }
        composable("MoodScreen"){
            MoodScreen(navController, chatViewModel)
        }
    }
}