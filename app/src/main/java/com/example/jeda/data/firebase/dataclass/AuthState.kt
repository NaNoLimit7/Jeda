package com.example.jeda.data.firebase.dataclass

import android.os.Message

sealed class AuthState {
    object Authenticated: AuthState()
    object Unauthenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String) : AuthState()
}