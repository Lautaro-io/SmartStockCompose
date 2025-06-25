package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.smartstock.ui.theme.SoftGray

@Composable
fun SectionTitle(text: String, color: Color = SoftGray, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        Text(
            text,
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            color = color,
            thickness = 1.dp,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        )
    }
}