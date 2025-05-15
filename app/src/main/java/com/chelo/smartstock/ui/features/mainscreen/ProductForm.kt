package com.chelo.smartstock.ui.features.mainscreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.data.entities.ProductEntity
import com.chelo.smartstock.ui.features.mainscreen.components.HeaderApp
import com.chelo.smartstock.ui.features.mainscreen.components.SectionTitle
import com.chelo.smartstock.ui.features.navigation.mainScreen
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.BranchViewModel
import com.chelo.smartstock.viewmodel.ProductViewModel
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductForm(navController: NavController) {

    val productViewModel: ProductViewModel = hiltViewModel()
    val branchViewModel: BranchViewModel = hiltViewModel()

    val allBranches = branchViewModel.allBranches.collectAsState(initial = emptyList())
    var selectedBranch by remember { mutableStateOf<BranchEntity?>(null) }


    var nameProduct by remember { mutableStateOf("") }
    var codeBar by remember { mutableStateOf("") }
    var countProduct by remember { mutableStateOf("") }

    var state = rememberDatePickerState()
    var showDateDialog by remember { mutableStateOf(false) }
    var date = state.selectedDateMillis
    var expireDate = ""


    var image: String? by remember { mutableStateOf("") }
    var expanded by remember{mutableStateOf(false)}

    date?.let {
        var localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        expireDate = "${localDate.dayOfMonth}/${localDate.month}/${localDate.year}"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderApp()
        SectionTitle("Agregar Producto")
        OutlinedTextField(
            value = nameProduct,
            onValueChange = { nameProduct = it },
            label = { Text("Nombre del producto") })

        OutlinedTextField(
            value = codeBar,
            onValueChange = { codeBar = it },
            label = { Text("Codigo de barra") })

        OutlinedTextField(
            value = countProduct,
            onValueChange = { countProduct = it },
            label = { Text("Cantidad") })
        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        ) {
            OutlinedTextField(
                value = selectedBranch?.branchName ?: "Seleccione una sucursal",
                onValueChange = {},
                readOnly = true,
                label = { Text("Seleccione una sucursal") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(expanded, onDismissRequest = { expanded = false }) {
                allBranches.value.forEach { branch ->
                    DropdownMenuItem(
                        onClick = {
                            Log.i("CHELO" , "${branch.branchName} ${branch.branchId} id gurdaddo ${selectedBranch?.branchId}"  )
                            selectedBranch = branch
                            expanded = false

                        },
                        text = { Text(branch.branchName) })
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { showDateDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundColor,
                contentColor = WhiteText
            )
        ) { Text("Agregar fecha de vencimiento") }
        Button(
            onClick = {
                productViewModel.getProductByCode(codeBar)
                nameProduct =
                    productViewModel.productResult.value?.product?.nameProduct ?: ""
                image = productViewModel.productResult.value?.product?.imageProduct

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundColor,
                contentColor = WhiteText
            )
        ) {
            Text("Buscar producto por codigo")
        }

        Button(onClick = {
            productViewModel.insertProduct(
                ProductEntity(
                    productId = 0,
                    nameProduct = nameProduct,
                    count = countProduct.toInt(),
                    expireDate = expireDate,
                    codeBar = codeBar,
                    image = image,
                    branchFkId = selectedBranch?.branchId ?: -1
                )
            )
            navController.navigate(mainScreen.route)
        }) { Text("Agregar producto") }

        if (showDateDialog) {
            DatePickerDialog(
                onDismissRequest = { showDateDialog = false },
                confirmButton = {
                    Button(onClick = { showDateDialog = false }) {
                        Text("Confirmar")
                    }
                },

                ) {
                DatePicker(state)
            }
        }
    }


}