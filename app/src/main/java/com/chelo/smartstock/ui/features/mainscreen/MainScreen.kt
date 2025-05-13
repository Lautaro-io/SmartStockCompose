package com.chelo.smartstock.ui.features.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chelo.smartstock.R
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.ui.features.mainscreen.components.HeaderApp
import com.chelo.smartstock.ui.features.mainscreen.components.ProductItem
import com.chelo.smartstock.ui.features.mainscreen.components.SectionTitle
import com.chelo.smartstock.ui.features.navigation.productForm
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(productForm.route) }, containerColor = ButtonBackground,
            contentColor = WhiteText
        ) { Icon(Icons.Default.Add, contentDescription = "Add Icon") }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            HeaderApp()

            Spacer(
                modifier = Modifier
                    .padding(8.dp)
                    .height(1.dp)
            )
            SectionTitle("Productos")
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                item {
                    ProductItem(
                        ProductEntity(
                            productId = 0,
                            nameProduct = "Quilmes",
                            count = 8,
                            expireDate =  "26",
                            codeBar =  "16516",
                            branchFkId =  5
                        )
                    )
                }
            }


        }
    }

}








