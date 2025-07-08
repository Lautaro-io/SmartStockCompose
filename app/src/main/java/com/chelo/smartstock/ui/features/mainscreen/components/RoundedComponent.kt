package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.WhiteText


@Composable
fun RoundedComponent(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, BackgroundColor),
        color = BackgroundColor
    ) {
        Text(
            text = text,
            color = WhiteText,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(8.dp)
        )

    }
}