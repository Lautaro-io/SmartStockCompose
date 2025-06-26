package com.chelo.smartstock.ui.features.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = "${productForm.route}?imagePath={imagePath}?productId={productId}",
            arguments = listOf(
                navArgument("imagePath") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                },
                navArgument("productId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val imagePath = backStackEntry.arguments?.getString("imagePath")
            val decodeImage = Uri.decode(imagePath ?: "")
            val productId = backStackEntry.arguments?.getLong("productId").takeIf { it != -1L }
            ProductForm(navController, decodeImage,productId)
        }
        composable(cameraScreen.route ){
            CameraScreen(navController)
        }

    }
}