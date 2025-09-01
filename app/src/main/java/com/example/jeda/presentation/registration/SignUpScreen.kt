package com.example.jeda.presentation.registration

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.R
import com.example.jeda.data.firebase.ViewModel.AuthViewModel
import com.example.jeda.data.firebase.dataclass.AuthState
import com.example.jeda.ui.theme.localFontFamily
import com.example.jeda.ui.theme.ungutua

//@Preview
@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var color1 by remember { mutableStateOf(Color.Gray) }
    if (email == "" || password == "" || confirmpassword == "") color1  else color1 = Color(ungutua.value)
    var isPasswordVisible by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                Toast.makeText(
                    context,
                    "Logging in...",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate("HomeScreen")
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(color = Color.Companion.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Text(
            "Logo",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Companion.Bold,
            fontSize = 25.sp
        )

        Spacer(Modifier.Companion.height(38.dp))

        Text(
            "Bergabunglah bersama kami!",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Companion.Bold,
            fontSize = 20.sp
        )

        Spacer(Modifier.Companion.height(8.dp))

        Text(
            "Buat akun Anda dan buka peluang baru.",
            fontFamily = localFontFamily,
            fontWeight = FontWeight.Companion.Normal,
            fontSize = 13.sp
        )

        Spacer(Modifier.Companion.height(8.dp))

        Column(
            modifier = Modifier.Companion
                .background(color = Color.Companion.White)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                Text(
                    "Alamat Email",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Companion.SemiBold,
                    fontSize = 13.sp
                )
            }

            OutlinedTextField(
                modifier = Modifier.Companion.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text("Email",fontFamily = localFontFamily,
                        fontWeight = FontWeight.Companion.Normal,
                        fontSize = 13.sp)
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.Companion.height(16.dp))

            Row(
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                Text(
                    "Kata sandi",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Companion.SemiBold,
                    fontSize = 13.sp
                )
            }

            OutlinedTextField(
                modifier = Modifier.Companion.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text("Password",fontFamily = localFontFamily,
                        fontWeight = FontWeight.Companion.Normal,
                        fontSize = 13.sp)
                },trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = if (isPasswordVisible) painterResource(R.drawable.baseline_visibility_24) else painterResource(
                                R.drawable.baseline_visibility_off_24
                            ), "", tint = color1
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                modifier = Modifier.Companion.fillMaxWidth(),
                value = confirmpassword,
                onValueChange = {
                    confirmpassword = it
                },
                label = {
                    Text("Konfirmasi Kata sandi",fontFamily = localFontFamily,
                        fontWeight = FontWeight.Companion.Normal,
                        fontSize = 13.sp)
                },trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            painter = if (isPasswordVisible) painterResource(R.drawable.baseline_visibility_24) else painterResource(
                                R.drawable.baseline_visibility_off_24
                            ), "", tint = color1
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.Companion.height(16.dp))

            Button(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(48.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                onClick = {
                    if (confirmpassword != password) {
                        Toast.makeText(context, "Password tidak sama.", Toast.LENGTH_SHORT).show()
                    } else if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Email atau password tidak boleh kosong.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (password.length < 8) {
                        Toast.makeText(
                            context,
                            "Password harus lebih panjang dari 7 karakter.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        authViewModel.signup(email, password)
                    }
                },
                enabled = authState.value != AuthState.Loading,
                colors = ButtonDefaults.buttonColors(color1)
            ) {
                Text(
                    "Daftar",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Companion.Normal,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.line), contentDescription = "")

                Spacer(Modifier.width(4.dp))
                Text(
                    "Atau",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Companion.Normal,
                    fontSize = 13.sp
                )
                Image(painter = painterResource(R.drawable.line), contentDescription = "")
            }

            Spacer(Modifier.Companion.height(24.dp))

            Button(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(48.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(painter = painterResource(R.drawable.google_icon),
                        contentDescription = "",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black)
                    Text(
                        "Lanjutkan dengan Google",
                        fontFamily = localFontFamily,
                        fontWeight = FontWeight.Companion.Normal,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier.height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Sudah punya akun?",
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Companion.Normal,
                    fontSize = 13.sp
                )
                TextButton(
                    modifier = Modifier.Companion
                        .height(35.dp)
                        .width(90.dp),
                    onClick = {
                        navController.navigate("LoginScreen")
                    }
                ) {
                    Text(
                        "Masuk",
                        fontFamily = localFontFamily,
                        fontWeight = FontWeight.Companion.Normal,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}