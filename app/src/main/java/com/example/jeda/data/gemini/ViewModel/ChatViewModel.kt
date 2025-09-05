package com.example.jeda.data.gemini.ViewModel

import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jeda.data.Constants
import com.example.jeda.data.gemini.UseCase.containsSadKeyword
import com.example.jeda.data.gemini.dataclass.MessageModel
import com.example.jeda.presentation.home.MessageList
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel(){

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    var userMood: String? = null

    val model = GenerativeModel(
        modelName = "gemini-2.5-pro",
        apiKey = Constants.apikey
    )

    fun updateUserMood(mood: String){
        userMood = mood

        messageList.add(
            MessageModel(
                "Aku merasa $mood hari ini.",
                "user"
            )
        )
        messageList.add(
            MessageModel(
                "Terima kasih sudah berbagi perasaanmu. Aku akan menyesuaikan jawabanku sesuai dengan moodmu.",
                "model"
            )
        )
    }

    fun sendMessage(question: String){
        viewModelScope.launch {

            try {
            messageList.add(MessageModel(question, "user"))
            messageList.add(MessageModel("Typing...", "model"))

            if (containsSadKeyword(question)){
                messageList.removeAt(messageList.lastIndex)
                messageList.add(
                    MessageModel(
                        "Saya mendengar kamu sedang merasa sedih. Tidak apa-apa untuk merasakan itu. Saya menyarankan kamu untuk berbicara dengan seorang psikolog agar mendapatkan dukungan lebih lanjut.",
                        "model"
                    )
                )
                return@launch
            }
            val chat = model.startChat(
                history = messageList.map {
                    content(it.sender) { text(it.message) }
                }.toList()
            )

            val moodPrompt = if (userMood != null){
                "User sedang merasa: $userMood.\nPertanyaan user: $question"
            }else {
                question
            }

            val response = chat.sendMessage(moodPrompt)
            messageList.removeAt(messageList.lastIndex)
            messageList.add(MessageModel(response.text.toString(), "model"))
            }catch (e : Exception){
            messageList.removeAt(messageList.lastIndex)
            messageList.add(MessageModel("Error: "+e.message.toString(),"model"))
            }

        }
    }

}

