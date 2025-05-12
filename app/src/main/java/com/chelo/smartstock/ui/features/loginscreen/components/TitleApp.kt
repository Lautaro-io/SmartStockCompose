package com.chelo.smartstock.ui.features.loginscreen.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chelo.smartstock.ui.theme.WhiteText

@Composable
fun TitleApp() {
    Text(text = "SMARTSTOCK", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = WhiteText)
}