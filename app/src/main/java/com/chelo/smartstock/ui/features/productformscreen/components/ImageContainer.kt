package com.chelo.smartstock.ui.features.productformscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chelo.smartstock.R
import com.chelo.smartstock.ui.features.navigation.cameraScreen
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.WhiteText
import java.io.File


@Composable
fun ImageContainer(
    modifier: Modifier = Modifier,
    imagePath: String?,
    imageUrl: String? = "",
    navController: NavController,
) {
    val file = imagePath?.let { File(it) }

    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (file != null && file.exists())
                Image(
                    painter = rememberAsyncImagePainter(file),
                    contentDescription = "",
                    modifier = modifier.fillMaxSize(), contentScale = ContentScale.Crop
                )
            else
                if (imageUrl?.isNotEmpty() == true || imageUrl != "") {
                    Image(
                        rememberAsyncImagePainter(imageUrl),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }else Image(
                    painterResource(R.drawable.defaul),
                    contentDescription = "",
                    modifier = modifier.fillMaxSize()
                )



        }
        FloatingActionButton(
            onClick = { navController.navigate(cameraScreen.route) },
            shape = CircleShape,
            modifier = modifier.align(alignment = Alignment.BottomEnd),
            contentColor = WhiteText,
            containerColor = BackgroundColor
        ) { Icon(Icons.Default.Add, contentDescription = "") }

    }
}