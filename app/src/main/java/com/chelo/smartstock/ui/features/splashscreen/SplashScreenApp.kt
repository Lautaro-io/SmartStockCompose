package com.chelo.smartstock.ui.features.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chelo.smartstock.R
import com.chelo.smartstock.data.datastore.DataStoreManager
import com.chelo.smartstock.ui.features.loginscreen.components.TitleApp
import com.chelo.smartstock.ui.features.navigation.mainScreen
import com.chelo.smartstock.ui.features.navigation.userLoginScreen
import com.chelo.smartstock.ui.theme.BackgroundColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreenApp(navController: NavController) {

    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val userId = dataStore.userIdFlow.collectAsState(-1)

    LaunchedEffect(key1 = true) {
        delay(1000)
        if (userId.value >= 0) {
            navController.navigate(mainScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(userLoginScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painterResource(R.drawable.supermarket), contentDescription = "App Logo")
            Spacer(modifier = Modifier.height(8.dp))
            TitleApp()
        }

    }
}
