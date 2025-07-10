package com.chelo.smartstock.ui.features.productformscreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.WhiteText

@Composable
fun PersonalizedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier : Modifier = Modifier,
    isNumber: Boolean = false,
) {
    val type = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
        keyboardType = KeyboardType.Text
    )
    OutlinedTextField(
        modifier = modifier.padding(vertical = 8.dp),
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(label) },
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = WhiteText,
            unfocusedContainerColor = WhiteText,
            focusedBorderColor = BackgroundColor,
            focusedLabelColor = BackgroundColor
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = type.keyboardType
        ),
    )


}