package com.example.jeda.presentation.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.presentation.data.BottomBar
import com.example.jeda.presentation.navigation.BottomNavGraph

@Composable
fun MainBottomNav(navController: NavController, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController)}
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
        ){
            BottomNavGraph(navController = navController, authViewModel = authViewModel)
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val screens = listOf(
        BottomBar.Home,
        BottomBar.Bot,
        BottomBar.Journal,
        BottomBar.User
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface(
        color = Color.Black,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(20.dp, 8.dp, 20.dp, 8.dp)
            .fillMaxWidth()
            .height(72.dp)
            .offset(y = -54.dp)
            .shadow(30.dp, shape = RoundedCornerShape(30.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                val selected = currentDestination?.route == screen.route

                val animatedWidth by animateDpAsState(
                    targetValue = if (selected) 90.dp else 56.dp,
                    label = "widthAnim"
                )

                Column(
                    modifier = Modifier
                        .height(56.dp)
                        .width(animatedWidth)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (selected) Color(0xFF853CFF) else Color.Transparent
                        )
                        .clickable {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                            contentDescription = screen.title,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = screen.title,
                            fontSize = 12.sp,
                            color = if (selected) Color.Black else Color.White,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

