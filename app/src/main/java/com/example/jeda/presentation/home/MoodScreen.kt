package com.example.jeda.presentation.home

import android.R.attr.label
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.ui.theme.localFontFamily

@Composable
fun MoodScreen(
    navController: NavController,
    onMoodSelected: (String) -> Unit
) {
    val moods = listOf(
        "Sangat buruk" to "😵",
        "Buruk" to "😟",
        "Netral" to "😐",
        "Baik" to "🙂",
        "Sangat baik" to "😁"
    )

    var selectedMood by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF853CFF))
            .padding(start = 20.dp, end = 20.dp, bottom = 30.dp, top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(painter = painterResource(R.drawable.botmoodscreen_logo), contentDescription = "")

        Spacer(Modifier.height(32.dp))

        Text("Apa yang sedang kamu rasakan saat ini?",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(13.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            moods.forEach { (label, emoji) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { selectedMood = label }
                        .padding(8.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                if (selectedMood == label) Color.White else Color.LightGray,
                                CircleShape
                            )
                    ) {
                        Text(emoji, fontSize = 28.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(label, color = Color.White, fontSize = 12.sp)


                }
            }
        }

        Spacer(modifier = Modifier.height(384.dp))

        Button(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            onClick = {
                selectedMood?.let { onMoodSelected(it) }
                navController.navigate("chat/{mood}") },
            enabled = selectedMood != null,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedMood != null) Color.Black else Color.Gray
            ),
            shape = RoundedCornerShape(12.dp)

        ) {
            Text("Ayo Mulai", color = Color.White)
        }
    }
}