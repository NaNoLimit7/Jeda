package com.example.jeda.data.gemini.UseCase

fun containsSadKeyword(text: String): Boolean {
    val keywords = listOf("terpuruk", "depresi", "down", "putus asa", "stress", "bunuh diri", "hancur")
    return keywords.    any { text.contains(it, ignoreCase = true) }
}