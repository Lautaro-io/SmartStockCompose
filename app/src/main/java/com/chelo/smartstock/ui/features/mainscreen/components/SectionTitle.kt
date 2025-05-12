package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.smartstock.ui.theme.SoftGray

@Composable
fun SectionTitle(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
//        Divider(
//            color = SoftGray, thickness = 1.dp, modifier = Modifier
//                .weight(1f)
//                .width(16.dp)
//        )
        Text(
            text,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = SoftGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(color = SoftGray, thickness = 1.dp, modifier = Modifier.weight(1f))
    }
}