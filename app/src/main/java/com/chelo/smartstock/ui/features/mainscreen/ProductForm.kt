package com.chelo.smartstock.ui.features.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.smartstock.ui.features.mainscreen.components.HeaderApp
import com.chelo.smartstock.ui.features.mainscreen.components.SectionTitle
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductForm() {

    var nameProduct by remember { mutableStateOf("") }
    var expireDate by remember { mutableStateOf("") }
    var codeBar by remember { mutableStateOf("") }
    var countProduct by remember { mutableStateOf("") }
    var state = rememberDatePickerState()
    var showDateDialog by remember { mutableStateOf(false) }
    var date = state.selectedDateMillis

    date?.let {
        var localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        HeaderApp()
        SectionTitle("Agregar Producto")
        OutlinedTextField(
            value = nameProduct,
            onValueChange = { nameProduct = it },
            label = { Text("Nombre del producto") })

        Button(
            onClick = { showDateDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBackground,
                contentColor = WhiteText
            )
        ) { Text("Fecha de vencimiento", fontSize = 18.sp) }

        if (showDateDialog) {
            DatePickerDialog(
                onDismissRequest = { showDateDialog = false },
                confirmButton = {
                    Button(onClick = { showDateDialog = false }) {
                        Text("Confirmar")
                    }
                },

                ) {
                DatePicker(state)
            }
        }
    }


}