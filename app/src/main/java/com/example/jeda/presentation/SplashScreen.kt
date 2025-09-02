package com.example.jeda.presentation

import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.firebase.dataclass.AuthState
import com.example.jeda.ui.theme.ungutua
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel) {
    val scale = remember {
        Animatable(0f)
    }

    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(key1 = true,authState.value) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        when(authState.value){
            is AuthState.Authenticated -> {
                Toast.makeText(
                    context,
                    "Logging in...",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate("MainBottomNav")
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> navController.navigate("onBoarding1")
        }

    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF853CFF)),
        contentAlignment = Alignment.Center
        ){
        Image(painter = painterResource(R.drawable.logo_jeda),
            contentDescription = "",
            modifier = Modifier.scale(scale.value))
    }
}