package com.example.jeda.presentation.home

import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.data.gemini.UseCase.containsSadKeyword
import com.example.jeda.data.gemini.ViewModel.ChatViewModel
//import com.example.jeda.data.gemini.ViewModel.getGeminiReply
//import com.example.jeda.data.gemini.dataclass.ChatMessage
import com.example.jeda.data.gemini.dataclass.MessageModel
import com.example.jeda.presentation.data.BottomBar
import com.example.jeda.ui.theme.localFontFamily
import kotlinx.coroutines.launch
import kotlin.collections.listOf

@Composable
fun BotScreen(
    navController: NavController,
//    userMood: String,
    chatViewModel: ChatViewModel
    ) {
//    val chatViewModel: ChatViewModel = viewModel()
//    var chatHistory by remember { mutableStateOf(listOf<ChatMessage>()) }
//    var inputText by remember { mutableStateOf("") }
//    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF853CFF))
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 55.dp)
    ){
        BotScreenHeader(navController)
        MessageList(modifier = Modifier.weight(1f), messageList = chatViewModel.messageList)
        MessageInput(onMessageSend = {
            chatViewModel.sendMessage(it)
        })
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier,messageList: List<MessageModel> ) {
    if (messageList.isEmpty()){
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(painter = painterResource(R.drawable.botscreen_logo)
                , contentDescription = "")

            Spacer(modifier = Modifier.height(12.dp))

            Text("Hai, senang bertemu denganmu.\n" +
                    "Aku Nala, AI pribadimu.",
                fontSize = 20.sp,
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White)

            Spacer(modifier = Modifier.height(12.dp))

            Text("Tujuanku adalah menjadi AI yang berguna, ramah, dan menyenangkan. Mintalah saran, jawaban, atau mari kita bicarakan apa pun yang ada di pikiranmu.",
                fontSize = 13.sp,
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = Color.White)
        }
    }else {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(messageList.reversed()){
            MessageRow(messageModel = it)
        }
    }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.sender == "model"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .align(if (isModel) Alignment.BottomStart else Alignment.BottomEnd)
                    .padding(
                        start = if (isModel) 0.dp else 70.dp,
                        end = if (isModel) 70.dp else 0.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .clip(
                        if (isModel) RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomStart = 12.dp, bottomEnd = 12.dp)
                                else RoundedCornerShape(topStart = 12.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 12.dp))
                    .background(Color.Black)
                    .padding(12.dp)
            ) {
                SelectionContainer {
                    Text(text = messageModel.message,
                        fontFamily = localFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White)
                }
            }
        }
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {

    var message by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = {
                message = it
            },
            placeholder = {Text("Tulis pesan...")},
            colors = TextFieldDefaults.colors(Color.Black),
            shape = RoundedCornerShape(12.dp)
        )
        IconButton(onClick = {
            if (message.isNotEmpty()){
            onMessageSend(message)
            message = ""
            }
        }) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = ""
                )
        }
    }
}

//@Preview
@Composable
fun BotScreenHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF853CFF)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(painter = painterResource(R.drawable.logout_icon ),
            contentDescription = "",
            modifier = Modifier.clickable(onClick = {
                navController.navigate(BottomBar.Home.route)
            }))

        Image(painterResource(R.drawable.botheader_logo), contentDescription = "")

        Image(painter = painterResource(R.drawable.botscreen2_logo), contentDescription = "")
    }
}


















//        LazyColumn (
//            modifier = Modifier.weight(1f),
//            reverseLayout = true
//        ){
//            items(chatHistory.reversed()) { msg ->
//                Box(
//                    modifier = Modifier.fillMaxWidth(),
//                    contentAlignment = if (msg.sender == "user") Alignment.CenterEnd else Alignment.CenterStart
//                ){
//                    Text(
//                        msg.message,
//                        modifier = Modifier.
//                        background(
//                            Color.Black,
//                            shape = if (msg.sender == "user"){
//                                RoundedCornerShape(topStart = 12.dp, topEnd = 0.dp, bottomEnd = 12.dp, bottomStart = 12.dp)
//                            } else
//                                RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp)
//                        )
//                            .padding(12.dp)
//                            .widthIn(max = 250.dp),
//                        color = Color.White
//                    )
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//        }
//
//        Row(Modifier.padding(top = 8.dp, bottom = 8.dp, start = 0.dp, end = 0.dp)) {
//            OutlinedTextField(
//                value = inputText,
//                onValueChange = { inputText = it },
//                placeholder = { Text("Tulis pesan...") },
//                modifier = Modifier.weight(1f),
//                colors = TextFieldDefaults.colors(Color.Black),
//                shape = RoundedCornerShape(12.dp),
//            )
//            Spacer(Modifier.width(8.dp))
//            Button(onClick = {
//                if (inputText.isNotBlank()) {
//                    val currentText = inputText
//                    inputText = ""
//                    scope.launch {
//                        val userMessage = ChatMessage("user", currentText)
//                        chatHistory = chatHistory + userMessage
//
//                        if (containsSadKeyword(currentText)) {
//                          val reply =  "Saya mendengar kamu sedang merasa sedih. " +
//                                    "Tidak apa-apa untuk merasakan itu. " +
//                                    "Saya menyarankan kamu untuk berbicara dengan seorang psikolog " +
//                                    "agar mendapatkan dukungan lebih lanjut. 💙"
//
//                            chatHistory = chatHistory + ChatMessage("bot", reply)
//                        } else {
//                            chatViewModel.sendMessage(userMood, currentText) { reply ->
//                                chatHistory = chatHistory + ChatMessage("bot", reply)
//                            }
//                        }
//                    }
//                }
//            },
//                colors = ButtonDefaults.buttonColors(Color.Black)
//            ) {
//                Text("Kirim")
//            }
//        }
