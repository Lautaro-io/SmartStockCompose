package com.chelo.smartstock.ui.features.loginscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.smartstock.R
import com.chelo.smartstock.data.datastore.DataStoreManager
import com.chelo.smartstock.data.entities.UserEntity
import com.chelo.smartstock.ui.features.loginscreen.components.TitleApp
import com.chelo.smartstock.ui.features.productformscreen.components.PersonalizedTextField
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.ButtonBackground
import com.chelo.smartstock.ui.theme.WhiteText
import com.chelo.smartstock.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserLoginScreen(navController: NavController) {

    val context = LocalContext.current
    val dataStore = DataStoreManager(context)
    val userViewModel: UserViewModel = hiltViewModel()


    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleApp()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Column(
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally ,
                modifier = Modifier.padding(top = 32.dp)) {
                Image(
                    painterResource(R.drawable.supermarket),
                    contentDescription = "Logo App",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    "Bienvenido!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = WhiteText,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Column (
                verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp)){
                PersonalizedTextField(value = name, onValueChange = { name = it }, label = "Nombre")
                PersonalizedTextField(
                    value = surname,
                    onValueChange = { surname = it },
                    label = "Apellido",
                    modifier = Modifier.padding(vertical = 4.dp)
                )


                Button(
                    onClick = {
                        if (listOf(name,surname).any{it.isEmpty()}) {
                            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val user = UserEntity(0, name, surname)
                            userViewModel.addUser(user) { userId ->
                                CoroutineScope(Dispatchers.IO).launch {

                                    dataStore.saveUserId(userId)
                                    dataStore.saveUserName(user.name)
                                }

                                Toast.makeText(context, "Usuario registrado.", Toast.LENGTH_SHORT)
                                    .show()

                                navController.navigate("branchloginscreen")
                            }
                        }


                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonBackground,
                        contentColor = WhiteText
                    )
                ) {
                    Text("Agregar usuario", fontSize = 18.sp)
                }
            }


        }
    }

}