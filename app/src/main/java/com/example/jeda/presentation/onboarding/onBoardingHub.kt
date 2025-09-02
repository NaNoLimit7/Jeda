package com.example.jeda.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onBoardingHub(navController: NavController) {

    val pagerState = rememberPagerState(0,0F) {
        3
    }

    Box(modifier = Modifier.fillMaxHeight()){
        HorizontalPager(state = pagerState) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center) {
                    when(pagerState.currentPage){
                        0 -> {
                            onBoarding2(
                                navController
                            )
                        }
                        1 -> {
                            onBoarding3(
                                navController
                            )
                        }
                        2 -> {
                            onBoarding4(
                                navController
                            )
                        }
                    }
                }
            }

        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp)
//                .align(Alignment.BottomCenter),
//                horizontalAlignment = Alignment.CenterHorizontally
//
//        ) {
//            Row {
//                repeat(3){
//                    CustomIndicator(isSelected = pagerState.currentPage == it)
//                }
//            }
//        }
    }
}

//@Composable
//fun CustomIndicator(isSelected: Boolean) {
//    Box(modifier = Modifier.padding(2.dp).background(color = if(isSelected) Color.Black else Color.White, shape = CircleShape).size(8.dp))
//}