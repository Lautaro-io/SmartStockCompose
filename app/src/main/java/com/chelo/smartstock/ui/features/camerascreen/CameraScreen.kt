package com.chelo.smartstock.ui.features.camerascreen

import android.Manifest
import android.net.Uri
import android.os.Environment
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.chelo.smartstock.R
import com.chelo.smartstock.ui.features.navigation.mainScreen
import com.chelo.smartstock.ui.features.navigation.productForm
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.io.File
import java.util.concurrent.Executor


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(navController: NavController) {
    val permission = rememberMultiplePermissionsState(
        listOf(Manifest.permission.CAMERA)
    )
    val context = LocalContext.current
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycle = LocalLifecycleOwner.current
    val direction =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absoluteFile

    LaunchedEffect(Unit) {
        permission.launchMultiplePermissionRequest()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(32.dp),
                onClick = {
                    val executor = ContextCompat.getMainExecutor(context)
                    takePicture(cameraController, executor, direction, navController)

                }) {
                Icon(
                    painterResource(R.drawable.baseline_camera_24),
                    contentDescription = "CameraIcon"
                )
            }
        }, floatingActionButtonPosition = FabPosition.Center
    ) {

        if (permission.allPermissionsGranted) {
            CameraView(cameraController, lifecycle, modifier = Modifier.padding(it))

        } else {
            navController.navigateUp()
        }

    }


}

@Composable
fun CameraView(
    cameraController: LifecycleCameraController,
    lifeCycle: LifecycleOwner,
    modifier: Modifier,
) {

    cameraController.bindToLifecycle(lifeCycle)
    AndroidView(
        modifier = modifier,
        factory = {
            val preview = PreviewView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            preview.controller = cameraController
            preview
        }
    )
}

fun takePicture(
    cameraController: LifecycleCameraController,
    executor: Executor,
    file: File,
    navController: NavController,
) {
    val image = File.createTempFile("ima_", ".png", file)
    val output = ImageCapture.OutputFileOptions.Builder(image).build()
    cameraController.takePicture(output, executor, object : ImageCapture.OnImageSavedCallback {
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val encodeImage = Uri.encode(image.absolutePath)
            navController.navigate("${productForm.route}?imagePath=$encodeImage") {
                popUpTo(
                    mainScreen.route
                )
            }
        }

        override fun onError(exception: ImageCaptureException) {
            return
        }

    })

}
