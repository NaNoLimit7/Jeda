package com.example.jeda.data.firebase.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jeda.data.firebase.dataclass.AuthState
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

        private val auth: FirebaseAuth = FirebaseAuth.getInstance()

        private val _authState = MutableLiveData<AuthState>()
        val authState: LiveData<AuthState> = _authState

        init {
        checkAuthStatus()
        }

        fun checkAuthStatus(){
            if (auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
            }else{
            _authState.value = AuthState.Authenticated
            }
        }

    fun login(email: String, password: String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email atau Password tidak bisa kosong")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Terjadi suatu kesalahan")
                }
            }
    }

    fun signup(email: String, password: String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email atau Password tidak bisa kosong")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Terjadi suatu kesalahan")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}