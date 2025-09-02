package com.example.jeda.presentation.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.ui.theme.localFontFamily

//@Preview
@Composable
fun onBoarding4(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().
        background(Color(0xFF853CFF)).
        padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.height(36.dp)
                    .width(92.dp),
                onClick = {
                    navController.navigate("LoginScreen")
                },
                border = BorderStroke(width = 1.dp, color = Color.Black),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text("Lewati",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Semua Berawal Dari",
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 31.sp,
                color = Color.White)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Langkah Pertama",
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 37.sp,
                color = Color.White)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(R.drawable.onboarding4_logo), contentDescription = "")

        Spacer(modifier = Modifier.height(84.5.dp))

        Text("Siap untuk memulai?",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            color = Color.White,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(48.5.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(painter = painterResource(R.drawable.right_onboarding_button),
                contentDescription = "",
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate("onBoarding3")
                    }
                ),
                tint = Color.Unspecified
            )
            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(R.drawable.slider_putih),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(5.dp))

                Icon(painter = painterResource(R.drawable.slider_putih),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(5.dp))

                Icon(painter = painterResource(R.drawable.slider_hitam),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }

            Icon(painter = painterResource(R.drawable.onboarding_button),
                contentDescription = "",
                tint = Color(0xFF853CFF)
            )
        }
    }
}