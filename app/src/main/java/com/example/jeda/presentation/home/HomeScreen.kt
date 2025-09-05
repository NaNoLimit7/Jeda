package com.example.jeda.presentation.home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.firebase.dataclass.AuthState
import com.example.jeda.ui.theme.localFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("LoginScreen")
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp))
                    .height(190.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(end = 34.5.dp, start = 14.5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .height(48.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row() {
                                Icon(painter = painterResource(R.drawable.calendar_icon), contentDescription = "")

                                Spacer(modifier = Modifier.width(4.dp))

                                Text("Tue, 25 Jan 2025",
                                    fontSize = 13.sp,
                                    fontFamily = localFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                    )
                            }

                            Icon(painter = painterResource(R.drawable.bell_icon),
                                contentDescription = "",
                                tint = Color.Unspecified)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .height(71.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Text("Hallo, Mutia",
                                    fontFamily = localFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 31.sp)

                                Spacer(modifier = Modifier.height(4.dp))

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(painter = painterResource(R.drawable.emoticondatar_icon),
                                        contentDescription = "",
                                        tint = Color.Unspecified)

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text("Bagaimana mood kamu hari ini?",
                                        fontFamily = localFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF7937E8))
                .padding(paddingValues)
                .padding(horizontal = 20.dp)

        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Card(
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier.fillMaxWidth()
                    .height(85.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .padding(8.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text("Terima kasih sudah bertahan sampai hari ini. Kamu sangat hebat.",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(185.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    colors = CardDefaults.cardColors(Color(0xFF9D63FF)),
                    modifier = Modifier.height(185.dp)
                        .width(176.dp)
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painter = painterResource(R.drawable.notepad_icon),
                                contentDescription = "",
                                tint = Color.White)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Health Journal",
                                fontFamily = localFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("31/365",
                            fontFamily = localFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White)

                        Spacer(modifier = Modifier.height(8.dp))

                        Image(painter = painterResource(R.drawable.date_logo), contentDescription = "")
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(Color(0xFF49218C)),
                    modifier = Modifier.height(185.dp)
                        .width(176.dp)
                        .clip(RoundedCornerShape(30.dp))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painter = painterResource(R.drawable.brain_icon),
                                contentDescription = "",
                                tint = Color.White)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Health Journal",
                                fontFamily = localFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("31/365",
                            fontFamily = localFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White)

                        Spacer(modifier = Modifier.height(8.dp))

                        Image(painter = painterResource(R.drawable.bothome_logo), contentDescription = "")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text("Mood Statistics",
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White)

            Icon(painter = painterResource(R.drawable.dotsvertical_icon), contentDescription = "",
            tint = Color.Unspecified)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(224.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.moodstatis_logo), contentDescription = "")
            }
        }
    }
}

//Column(
//modifier = Modifier.fillMaxSize(),
//verticalArrangement = Arrangement.Center,
//horizontalAlignment = Alignment.CenterHorizontally
//) {
//    Text("Home Screen", fontFamily = localFontFamily, fontWeight = FontWeight.Normal, fontSize = 20.sp)
//
//    TextButton(
//        onClick = {
//            authViewModel.signout()
//        }) {
//        Text("Sign Out")
//    }
//}