package com.example.jeda.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.data.gemini.UseCase.containsSadKeyword
import com.example.jeda.data.gemini.ViewModel.getGeminiReply
import com.example.jeda.data.gemini.dataclass.ChatMessage
import kotlinx.coroutines.launch
import kotlin.collections.listOf

@Composable
fun BotScreen(
    navController: NavController,
    userMood: String
    ) {
    var chatHistory by remember { mutableStateOf(listOf<ChatMessage>()) }
    var inputText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF853CFF))
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 55.dp)
    ){
        BotScreenHeader()
        LazyColumn (
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ){
            items(chatHistory.reversed()) { msg ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (msg.sender == "user") Alignment.CenterEnd else Alignment.CenterStart
                ){
                    Text(
                        msg.message,
                        modifier = Modifier.
                        background(
                            Color.Black,
                            shape = if (msg.sender == "user"){
                                RoundedCornerShape(topStart = 12.dp, topEnd = 0.dp, bottomEnd = 12.dp, bottomStart = 12.dp)
                            } else
                                RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp)
                        )
                            .padding(12.dp)
                            .widthIn(max = 250.dp),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row(Modifier.padding(top = 8.dp, bottom = 8.dp, start = 0.dp, end = 0.dp)) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Tulis pesan...") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.colors(Color.Black),
                shape = RoundedCornerShape(12.dp),
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    val currentText = inputText
                    inputText = ""
                    scope.launch {
                        val userMessage = ChatMessage("user", currentText)
                        chatHistory = chatHistory + userMessage

                        val reply = if (containsSadKeyword(currentText)) {
                            "Saya mendengar kamu sedang merasa sedih. " +
                                    "Tidak apa-apa untuk merasakan itu. " +
                                    "Saya menyarankan kamu untuk berbicara dengan seorang psikolog " +
                                    "agar mendapatkan dukungan lebih lanjut. 💙"
                        } else {
                            getGeminiReply(userMood, currentText)
                        }

                        val botMessage = ChatMessage("bot", reply)
                        chatHistory = chatHistory + botMessage
                    }
                }
            },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text("Kirim")
            }
        }
    }
}

@Preview
@Composable
fun BotScreenHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF853CFF)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(painter = painterResource(R.drawable.logout_icon ), contentDescription = "")

        Image(painterResource(R.drawable.botheader_logo), contentDescription = "")

        Image(painter = painterResource(R.drawable.botscreen_logo), contentDescription = "")
    }
}