package com.example.jeda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.gemini.ViewModel.ChatViewModel
import com.example.jeda.presentation.navigation.NavGraph
import com.example.jeda.ui.theme.JedaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()
        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        setContent {
            NavGraph(
                authViewModel = authViewModel,
                chatViewModel)
        }
    }
}

