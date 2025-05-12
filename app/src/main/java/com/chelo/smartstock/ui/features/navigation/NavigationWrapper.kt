package com.chelo.smartstock.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.smartstock.data.datastore.DataStoreManager
import com.chelo.smartstock.ui.features.loginscreen.BranchLoginScreen
import com.chelo.smartstock.ui.features.loginscreen.UserLoginScreen
import com.chelo.smartstock.ui.features.mainscreen.MainScreen

import com.chelo.smartstock.ui.features.splashscreen.SplashScreenApp


@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()
    NavHost(navController, startDestination = splashScreen.route) {

        composable(splashScreen.route) {
            SplashScreenApp(navController)
        }
        composable(userLoginScreen.route) {
            UserLoginScreen(navController)
        }
        composable(branchLoginScreen.route) {
            BranchLoginScreen(navController)
        }

        composable(mainScreen.route) {
            MainScreen()
        }

    }
}