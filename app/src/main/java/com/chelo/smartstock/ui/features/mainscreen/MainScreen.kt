package com.chelo.smartstock.ui.features.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen() {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { println("") }, containerColor = ButtonBackground,
            contentColor = WhiteText
        ) { Icon(Icons.Default.Add, contentDescription = "Add Icon") }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }

}




