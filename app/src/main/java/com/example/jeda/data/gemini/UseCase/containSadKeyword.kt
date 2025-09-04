package com.example.jeda.data.gemini.UseCase

fun containsSadKeyword(text: String): Boolean {
    val keywords = listOf("sedih", "depresi", "down", "putus asa", "stress")
    return keywords.    any { text.contains(it, ignoreCase = true) }
}