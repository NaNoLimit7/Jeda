package com.example.jeda.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.firebase.dataclass.AuthState
import com.example.jeda.ui.theme.localFontFamily

@Composable
fun UserScreen(
    navController: NavController,
    authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("LoginScreen")
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
) {
    Text("Home Screen", fontFamily = localFontFamily, fontWeight = FontWeight.Normal, fontSize = 20.sp)

    TextButton(
        onClick = {
            authViewModel.signout()
        }) {
        Text("Sign Out", color = Color.White)
    }
}
}