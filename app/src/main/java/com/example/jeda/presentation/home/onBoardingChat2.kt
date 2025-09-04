package com.example.jeda.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.jeda.R
import com.example.jeda.ui.theme.localFontFamily

@Preview
@Composable
fun onBoardingChat2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF853CFF))
            .padding(start = 20.dp, end = 20.dp,top = 60.dp, bottom = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 20.dp)
        ) {
            Icon(painter = painterResource(R.drawable.left_arrow_icon), contentDescription = "")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Apa yang sedang kamu rasakan saat ini?",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Bagaimana perasaanmu hari ini?",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            color = Color.White)

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth()
                .height(48.dp),
            onClick = {

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