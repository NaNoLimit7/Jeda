package com.example.jeda.data.gemini.ViewModel

import com.example.jeda.data.Constants
import com.google.ai.client.generativeai.GenerativeModel

suspend fun getGeminiReply(userMood: String, userMessage: String): String {
    val model = GenerativeModel(
        modelName = "gemini-2.5-pro",
        apiKey = Constants.apikey
    )

    val prompt = """
        User sedang merasa: $userMood
        Pertanyaan user: $userMessage
        Jawablah dengan ramah, empatik, dan sesuai konteks emosinya.
    """.trimIndent()

    return try {
        val response = model.generateContent(prompt)
        response.text ?: "Maaf, saya tidak bisa menjawab."
    } catch (e: Exception) {
        "Maaf, terjadi error saat menjangkau layanan AI."
    }
}