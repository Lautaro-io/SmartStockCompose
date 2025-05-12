package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.WhiteText

@Composable
fun ProductItem(product: ProductEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal = 24.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(BackgroundColor, WhiteText)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(product.nameProduct)
            Text(product.expireDate)
            Text(product.count.toString())
        }

    }

}