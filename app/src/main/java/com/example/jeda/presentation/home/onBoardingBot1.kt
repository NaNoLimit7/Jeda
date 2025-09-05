package com.example.jeda.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.presentation.data.BottomBar
import com.example.jeda.ui.theme.localFontFamily

//@Preview
@Composable
fun onBoardingBot1(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF853CFF))
            .padding(start = 20.dp, end = 20.dp,top = 5.dp, bottom = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 20.dp)
        ) {
            Icon(painter = painterResource(R.drawable.left_arrow_icon),
                contentDescription = "",
                modifier = Modifier.clickable( onClick = {
                    navController.navigate(BottomBar.Home.route)
                }
                ))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Selamat datang. Di sini adalah ruang aman untukmu berbagi cerita.",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White)

        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(R.drawable.onboardingchat_logo), contentDescription = "")

        Spacer(modifier = Modifier.height(47.dp))

        Button(
            modifier = Modifier.fillMaxWidth()
                .height(48.dp),
            onClick = {
                navController.navigate("MoodScreen")
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