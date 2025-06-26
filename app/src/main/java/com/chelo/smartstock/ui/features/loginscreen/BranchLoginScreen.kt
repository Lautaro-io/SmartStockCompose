package com.chelo.smartstock.ui.features.loginscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.smartstock.R
import com.chelo.smartstock.data.datastore.DataStoreManager
import com.chelo.smartstock.data.entities.BranchEntity
import com.chelo.smartstock.ui.features.loginscreen.components.TitleApp
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.BranchViewModel


@Composable
fun BranchLoginScreen(navController: NavController) {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val userName by dataStore.userNameFlow.collectAsState("")
    val userId by dataStore.userIdFlow.collectAsState(0)
    var branchName by remember { mutableStateOf("") }
    val branchViewModel: BranchViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleApp()
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(R.drawable.supermarket),
                contentDescription = "Logo App",
                modifier = Modifier.size(220.dp)
            )
            Text(
                "Hola ${userName}!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = WhiteText
            )
            Text(
                "Por favor agregue al menos una sucursal.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = WhiteText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = branchName,
                onValueChange = { branchName = it },
                placeholder = { Text("Nombre de la sucursal") },
                colors = TextFieldDefaults.colors(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

        }
        Button(
            onClick = {
                if (branchName.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Complete el campo",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    branchViewModel.addBranch(BranchEntity(branchName = branchName , userIdFk = userId))
                    Toast.makeText(context, "Sucursal agregada", Toast.LENGTH_SHORT).show()
                    navController.navigate("mainscreen"){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBackground,
                contentColor = WhiteText
            )
        ) { Text("Agregar sucursal") }


    }
}