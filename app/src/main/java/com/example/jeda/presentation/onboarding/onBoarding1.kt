package com.example.jeda.presentation.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.firebase.dataclass.AuthState
import com.example.jeda.ui.theme.localFontFamily

//@Preview
@Composable
fun onBoarding1(
    navController: NavController,
    authViewModel: AuthViewModel

) {
    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                Toast.makeText(
                    context,
                    "Logging in...",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate("HomeScreen")
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().
        background(Color(0xFF853CFF)).
        padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("JEDA",
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 49.sp,
                color = Color.White)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Jiwaku, Emosiku dan Aku",
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(R.drawable.onboarding1_logo), contentDescription = "")

        Spacer(modifier = Modifier.height(40.dp))

        Text("Karena setiap hal tentang kamu itu berharga",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = Color.White)

        Spacer(modifier = Modifier.height(46.5.dp))

        Button(
            modifier = Modifier.fillMaxWidth()
                .height(48.dp),
            onClick = {
                navController.navigate("onBoardingHub")
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color.Black))
            {
                Text("Mulai Perjalananmu",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.White)
            }

    }
}