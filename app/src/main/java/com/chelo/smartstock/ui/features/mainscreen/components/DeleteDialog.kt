package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.chelo.smartstock.ui.theme.DarkBackground
import com.chelo.smartstock.ui.theme.ErrorRed

@Composable
fun DeleteDialog(onDismissClick: () -> Unit, onConfirmClick: () -> Unit) {


    AlertDialog(
        onDismissRequest = { onDismissClick },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text("Confirmar", color = ErrorRed)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissClick }) {
                Text(
                    "Cancelar",
                    color = DarkBackground
                )
            }
        },
        title = {Text("Borrar Producto")},
        text = {Text("Desea eliminar el producto?")}
    )
}