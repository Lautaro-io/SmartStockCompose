package com.chelo.smartstock.ui.features.productformscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.smartstock.ui.features.mainscreen.components.HeaderApp
import com.chelo.smartstock.ui.features.navigation.mainScreen
import com.chelo.smartstock.ui.features.productformscreen.components.ImageContainer
import com.chelo.smartstock.ui.features.productformscreen.components.PersonalizedTextField
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.BranchViewModel
import com.chelo.smartstock.viewmodel.ProductViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductForm(navController: NavController, decodeImage: String?, productId: Long?) {

    val pvm: ProductViewModel = hiltViewModel()
    val branchViewModel: BranchViewModel = hiltViewModel()

    val allBranches = branchViewModel.allBranches.collectAsState(initial = emptyList())

    var showDateDialog by remember { mutableStateOf(false) }


    var state = rememberDatePickerState()
    var date = state.selectedDateMillis

    LaunchedEffect(Unit) {
        productId?.let {
            pvm.loadProduct(it)
        }
        decodeImage?.let {
            pvm.onImageChanged(it)
        }
    }


    var expanded by remember { mutableStateOf(false) }

    date?.let {
        var localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        val format = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
        Log.i("CHELO" , "Fecha : $format ")
        pvm.onExpireDateChange(format)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(BackgroundColor),
                title = {
                    Text(
                        "Agregar Producto",
                        color = WhiteText,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = WhiteText
                        )
                    }
                }

            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderApp(
                modifier = Modifier
                    .padding()
                    .height(150.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageContainer(
                    imagePath = decodeImage,
                    navController = navController,
                    imageUrl = pvm.image,
                    isLoading = pvm.spinnerStatus
                )
                PersonalizedTextField(
                    value = pvm.nameProduct,
                    onValueChange = { pvm.onNameChanged(it) },
                    label = "Nombre del producto"
                )

                PersonalizedTextField(
                    value = pvm.codeBar,
                    onValueChange = { pvm.onCodebarChanged(it) },
                    label = ("Codigo de barra"),
                    isNumber = true
                )

                PersonalizedTextField(
                    value = pvm.countProduct,
                    onValueChange = { pvm.onCountChanged(it) },
                    label = ("Cantidad"),
                    isNumber = true
                )
                Spacer(modifier = Modifier.height(8.dp))



                ExposedDropdownMenuBox(
                    expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 55.dp)
                ) {
                    OutlinedTextField(
                        value = branchViewModel.selectedBranch?.branchName ?: "Seleccione una sucursal",
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Seleccione una sucursal") },
                        shape = RoundedCornerShape(32.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = BackgroundColor,
                            focusedLabelColor = BackgroundColor
                        ),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded, onDismissRequest = { expanded = false }) {
                        allBranches.value.forEach { branch ->
                            DropdownMenuItem(
                                onClick = {
                                    branchViewModel.selectBranch(branch)
                                    pvm.selectBranch(branch.branchId)
                                    expanded = false

                                },
                                text = { Text(branch.branchName) })
                        }
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    Button(
                        onClick = { showDateDialog = true },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonBackground,
                            contentColor = WhiteText
                        )
                    ) { Text("Agregar fecha de vencimiento") }
                    Button(
                        onClick = {
                            pvm.getProductByCode(pvm.codeBar)

                        }, modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonBackground,
                            contentColor = WhiteText
                        )
                    ) {
                        Text("Buscar producto por codigo")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        pvm.saveProduct(productId )
                        navController.navigate(mainScreen.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackgroundColor,
                        contentColor = WhiteText
                    )
                ) {
                    Text(
                        if (productId != null) "Actualizar producto" else "Agregar producto",
                        fontSize = 18.sp
                    )
                }

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
    }


}