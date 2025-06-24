package com.chelo.smartstock.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.smartstock.ui.features.camerascreen.CameraScreen
import com.chelo.smartstock.ui.features.loginscreen.BranchLoginScreen
import com.chelo.smartstock.ui.features.loginscreen.UserLoginScreen
import com.chelo.smartstock.ui.features.mainscreen.MainScreen
import com.chelo.smartstock.ui.features.productformscreen.ProductForm
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
            MainScreen(navController)
        }
        composable(productForm.route) {
            ProductForm(navController)
        }
        composable(cameraScreen.route ){
            CameraScreen()
        }

    }
}