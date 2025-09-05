package com.example.jeda.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jeda.presentation.data.Notes
import com.example.jeda.ui.theme.localFontFamily
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun InsertNotesScreen(
    navController: NavController,
    id: String?
) {

    val context = LocalContext.current

    val db = FirebaseFirestore.getInstance()
    val notesDBRef = db.collection("notes")

    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        if (id != "defaultId"){
            notesDBRef.document(id.toString()).get().addOnSuccessListener {
                val singleData = it.toObject(Notes::class.java)
                title.value = singleData!!.title
                description.value = singleData.description
            }
        }
    }

    Scaffold(
        floatingActionButton ={
            FloatingActionButton(
                contentColor = Color.White,
                containerColor = Color.Black,
                onClick = {
                    if (title.value.isEmpty() && description.value.isEmpty()){
                        Toast.makeText(context, "Masukkan judul dan deskripsi", Toast.LENGTH_SHORT).show()
                    }else {

                        var myNotesId = ""

                        myNotesId = if (id != "defaultId"){
                            id.toString()
                        }else {
                            notesDBRef.document().id
                        }
                        val notes = Notes(
                            id = myNotesId,
                            title = title.value,
                            description = description.value
                        )
                        notesDBRef.document(myNotesId).set(notes).addOnCompleteListener {
                            if (it.isSuccessful){
                                Toast.makeText(
                                    context, "Note berhasil dimasukkan", Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("JournalScreen")
                            }else{
                                Toast.makeText(context, "Terjadi suatu kesalahan", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }


                    }
                }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "")
            }
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF853CFF))
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Masukkan Note",
                fontFamily = localFontFamily,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                textStyle = TextStyle(color = Color.Black,
                    fontFamily = localFontFamily,
                    fontWeight = FontWeight.Normal
                    ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                placeholder = {
                    Text("Masukkan judul",
                        fontWeight = FontWeight.Normal,
                        fontFamily = localFontFamily)
                },
                value = title.value, onValueChange = {
                    title.value = it
                }, modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                textStyle = TextStyle(color = Color.Black,
                fontFamily = localFontFamily,
                fontWeight = FontWeight.Normal),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                placeholder = {
                    Text("Masukkan deskripsi",
                        fontWeight = FontWeight.Normal,
                        fontFamily = localFontFamily)
                },
                value = description.value , onValueChange = {
                    description.value = it
                }, modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.6f)
            )
        }
    }
}