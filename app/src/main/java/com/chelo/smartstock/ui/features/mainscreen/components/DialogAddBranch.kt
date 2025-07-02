package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.ErrorRed
import com.chelo.smartstock.ui.theme.WhiteText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAddBranch(
    name: String,
    onDismissRequest: () -> Unit,
    onConfirmButton: () -> Unit,
    onValueChange: (String) -> Unit,
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(WhiteText),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Nueva sucursal")
                OutlinedTextField(
                    name,
                    onValueChange = { onValueChange(it) },
                    shape = RoundedCornerShape(32.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BackgroundColor,
                        focusedLabelColor = BackgroundColor
                    ),
                    label = { Text("Nombre de la sucursal") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Sentences,
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick = onDismissRequest,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ErrorRed,
                            contentColor = WhiteText
                        )
                    ) { Text("Cancelar") }

                    Button(
                        onClick = onConfirmButton,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BackgroundColor,
                            contentColor = WhiteText
                        )
                    ) { Text("Confirmar") }
                }

            }

        }
    }

}