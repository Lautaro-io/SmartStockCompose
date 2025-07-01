package com.chelo.smartstock.ui.features.mainscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.BlackText
import com.chelo.smartstock.ui.theme.ErrorRed
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.ProductViewModel

@Composable
fun ProductItem(product: ProductEntity, onEditButton: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    val productViewModel: ProductViewModel = hiltViewModel()
    val image = product.image.trim()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = WhiteText, contentColor = BlackText),
        onClick = { expanded = !expanded }
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentDescription = "product img",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .padding(4.dp)

            )
            Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {

                Text(product.nameProduct, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text("Codigo: ${product.codeBar}")
                Text("Cantidad: ${product.count}")
                Text("Vencimiento: ${product.expireDate}")
                Spacer(modifier = Modifier.height(4.dp))
                AnimatedVisibility(expanded) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { onEditButton() },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = WhiteText,
                                containerColor = BackgroundColor
                            ),
                            modifier = Modifier.weight(1f)
                        ) { Text("Editar") }
                        Button(
                            onClick = { showDeleteDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = WhiteText,
                                containerColor = ErrorRed
                            ),
                            modifier = Modifier.weight(1f)

                        ) { Text("Eliminar") }
                    }
                    if (showDeleteDialog) {
                        DeleteDialog(
                            onDismissClick = { showDeleteDialog = false },
                            onConfirmClick = {
                                productViewModel.deleteProduct(product)
                                showDeleteDialog = false
                            })
                    }

                }
            }

        }

    }

}