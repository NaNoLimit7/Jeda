package com.example.jeda.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jeda.ui.theme.localFontFamily
import com.example.jeda.ui.theme.ungumuda
import com.example.jeda.ui.theme.ungutua

@Preview
@Composable
private fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Logo", fontFamily = localFontFamily, fontWeight = FontWeight.Bold, fontSize = 25.sp)

        Spacer(Modifier.height(38.dp))

        Text("Selamat datang kembali!", fontFamily = localFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Spacer(Modifier.height(8.dp))

        Text("Masuk untuk melanjutkan dari tempat terakhir.", fontFamily = localFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp)

        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier.background(color = Color.White).padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Alamat Email", fontFamily = localFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                                 },
                label = {
                    Text("Email")
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kata sandi", fontFamily = localFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text("Password")
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(Color(ungumuda.value))
            ) {
                Text("Masuk", fontFamily = localFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp)
            }

            Row(modifier = Modifier.height(40.dp),
                verticalAlignment = Alignment.CenterVertically){
                Text("Baru disini?", fontFamily = localFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp)
                TextButton(
                    modifier = Modifier.height(35.dp).width(90.dp),
                    onClick = {

                }
                ){
                    Text("Buat akun", fontFamily = localFontFamily, fontWeight = FontWeight.Normal, fontSize = 13.sp)
                }

            }

        }
    }
}