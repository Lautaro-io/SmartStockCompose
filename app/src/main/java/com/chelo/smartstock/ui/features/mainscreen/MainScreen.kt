package com.chelo.smartstock.ui.features.mainscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.smartstock.ui.features.mainscreen.components.EmptyComponent
import com.chelo.smartstock.ui.features.mainscreen.components.HeaderApp
import com.chelo.smartstock.ui.features.mainscreen.components.ProductItem
import com.chelo.smartstock.ui.features.mainscreen.components.SectionTitle
import com.chelo.smartstock.ui.features.navigation.productForm
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.ProductViewModel

@Composable
fun MainScreen(navController: NavController) {
    val productViewModel: ProductViewModel = hiltViewModel()
    var allProducts = productViewModel.allProducts.collectAsState(emptyList())
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.padding(24.dp),
            onClick = { navController.navigate(productForm.route) },
            containerColor = ButtonBackground,
            contentColor = WhiteText
        ) { Icon(Icons.Default.Add, contentDescription = "Add Icon") }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderApp()

            Spacer(
                modifier = Modifier
                    .padding(innerPadding)
                    .height(1.dp)
            )
            SectionTitle("Productos", modifier = Modifier.padding(horizontal = 8.dp))

            if (allProducts.value.isEmpty())
                EmptyComponent()
            else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    itemsIndexed(allProducts.value.reversed()) { index, product ->
                        ProductItem(
                            product,
                            onEditButton = { navController.navigate("${productForm.route}?imagePath=${product.image}?productId=${product.productId}") })
                    }
                }


        }
    }

}








