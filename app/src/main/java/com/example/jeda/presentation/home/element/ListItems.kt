package com.example.jeda.presentation.home.element

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.jeda.presentation.data.Notes
import com.example.jeda.ui.theme.localFontFamily
import com.google.firebase.firestore.CollectionReference

@Composable
fun ListItems(notes: Notes, notesDBRef: CollectionReference, navController: NavController) {

    var expended by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
            .background(color = Color.Black)
    ) {

        DropdownMenu(modifier = Modifier.background(Color.White),
            properties = PopupProperties(clippingEnabled = true),
            offset = DpOffset(x = (-40).dp, y = 0.dp),
            expanded = expended,
            onDismissRequest = {
            expended = false
        }) {
            DropdownMenuItem(text = { Text(text = "Update", style = TextStyle(color = Color.Gray))},
                onClick = {
                    navController.navigate("InsertNotesScreen" + "/${notes.id}")
            })
            DropdownMenuItem(text = {Text(text = "Delete", style = TextStyle(color = Color.Gray))},
                onClick = {

                    val alertDialog = AlertDialog.Builder(context)
                    alertDialog.setMessage("Yakin ingin menghapus note ini?")
                    alertDialog.setPositiveButton("Iya"
                    ) { dialog, which ->
                        notesDBRef.document(notes.id).delete()
                        dialog?.dismiss()
                        expended = false
                    }

                    alertDialog.setNegativeButton(
                        "Tidak"
                    ) { dialog, which ->
                        dialog?.dismiss()
                        expended = false
                    }

                    alertDialog.show()
            })
        }

        Icon(imageVector = Icons.Default.MoreVert,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(10.dp)
                .clickable{
                    expended = true
                }
        )
        Column(modifier = Modifier.padding(20.dp)){
            Text(
                text = notes.title, fontFamily = localFontFamily, color = Color.White
            )
            Text(text = notes.description, fontFamily = localFontFamily, color = Color.White)
        }
    }
}