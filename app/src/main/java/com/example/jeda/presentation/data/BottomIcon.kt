package com.example.jeda.presentation.data

import com.example.jeda.R

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    object Home: BottomBar(
        route = "HomeScreen",
        title = "Home",
        icon = R.drawable.home_icon,
        icon_focused = R.drawable.home_black
    )

    object Bot: BottomBar(
        route = "onBoardingBot1",
        title = "Nala",
        icon = R.drawable.bot_icon,
        icon_focused = R.drawable.bot_black
    )

    object Journal: BottomBar(
        route = "JournalScreen",
        title = "Journal",
        icon = R.drawable.notepad_icon,
        icon_focused = R.drawable.notepad_black
    )

    object User: BottomBar(
        route = "UserScreen",
        title = "Profil",
        icon = R.drawable.user_icon,
        icon_focused = R.drawable.user_black
    )
}