package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.R
import com.chelo.smartstock.ui.theme.BackgroundColor

@Composable
fun HeaderApp(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .height(250.dp)
            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .background(
                BackgroundColor
            ),
        contentAlignment = Alignment.Center
    ) {
        Column {

            Image(painterResource(R.drawable.supermarket), contentDescription = "App Icon")
        }
        ///BOXSCOPE DENTRO DE LO AZUL
    }
}