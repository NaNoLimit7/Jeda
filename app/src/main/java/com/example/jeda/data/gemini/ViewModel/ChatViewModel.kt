package com.example.jeda.data.gemini.ViewModel

import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jeda.data.Constants
import com.example.jeda.data.gemini.dataclass.MessageModel
import com.example.jeda.presentation.home.MessageList
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel(){

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    val model = GenerativeModel(
        modelName = "gemini-2.5-pro",
        apiKey = Constants.apikey
    )

    fun sendMessage(question: String){
        viewModelScope.launch {

            try {
            val chat = model.startChat(
                history = messageList.map {
                    content(it.sender) { text(it.message) }
                }.toList()
            )

            messageList.add(MessageModel(question, "user"))
            messageList.add(MessageModel("Typing...", "model"))

            val response = chat.sendMessage(question)
            messageList.removeAt(messageList.lastIndex)
            messageList.add(MessageModel(response.text.toString(), "model"))
            }catch (e : Exception){
            messageList.removeAt(messageList.lastIndex)
            messageList.add(MessageModel("Error: "+e.message.toString(),"model"))
            }

        }
    }

}
//    fun sendMessage(userMood: String, userMessage: String, onReply: (String) -> Unit) {
//        viewModelScope.launch {
//            val prompt = """
//                User sedang merasa: $userMood
//                Pertanyaan user: $userMessage
//                Jawablah dengan ramah, empatik, dan sesuai konteks emosinya.
//            """.trimIndent()
//
//            val reply = try {
//                val response = model.generateContent(prompt)
//                response.text ?: "Maaf, saya tidak bisa menjawab."
//            } catch (e: Exception) {
//                "Maaf, terjadi error saat menjangkau layanan AI."
//            }
//            onReply(reply)
//        }
//    }

