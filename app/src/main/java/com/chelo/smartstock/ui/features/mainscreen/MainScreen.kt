package com.chelo.smartstock.ui.features.mainscreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.smartstock.data.datastore.DataStoreManager
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.ui.features.mainscreen.components.BranchSection
import com.chelo.smartstock.ui.features.mainscreen.components.DialogAddBranch
import com.chelo.smartstock.ui.features.mainscreen.components.EmptyComponent
import com.chelo.smartstock.ui.features.mainscreen.components.HeaderApp
import com.chelo.smartstock.ui.features.mainscreen.components.ProductItem
import com.chelo.smartstock.ui.features.mainscreen.components.SectionTitle
import com.chelo.smartstock.ui.features.navigation.productForm
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.BranchViewModel
import com.chelo.smartstock.viewmodel.MainViewmodel
import com.chelo.smartstock.viewmodel.ProductViewModel

@Composable
fun MainScreen(navController: NavController) {
    val productViewModel: ProductViewModel = hiltViewModel()
    val bvm: BranchViewModel = hiltViewModel()
    val mvm: MainViewmodel = hiltViewModel()
    val products = mvm.products.collectAsState()
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val userId by dataStore.userIdFlow.collectAsState(0)
    var showDialogBranch by remember { mutableStateOf(false) }

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

            BranchSection(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                bvm,
                onButtonClick = { showDialogBranch = true }) {
                mvm.selectBranch(it)
                mvm.changeState(true)
            }
            SectionTitle("Productos", modifier = Modifier.padding(horizontal = 8.dp))

            if (products.value.isEmpty())
                EmptyComponent()
            else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    itemsIndexed(products.value.reversed()) { index, product ->
                        ProductItem(
                            productViewModel,
                            product,
                            onEditButton = { navController.navigate("${productForm.route}?imagePath=${product.image}?productId=${product.productId}") })
                    }
                }


        }
        when {
            showDialogBranch -> DialogAddBranch(
                bvm.newBranch,
                onDismissRequest = { showDialogBranch = false },
                onConfirmButton = {
                    bvm.addBranch(
                        BranchEntity(
                            branchName = bvm.newBranch,
                            userIdFk = userId
                        )
                    )
                    showDialogBranch = false
                },
                onValueChange = { bvm.onNameBranchChange(it) }
            )
        }


    }

}








