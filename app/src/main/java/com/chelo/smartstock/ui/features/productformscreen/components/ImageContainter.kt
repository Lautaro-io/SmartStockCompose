package com.chelo.smartstock.ui.features.productformscreen.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chelo.smartstock.R
import com.chelo.smartstock.ui.theme.BackgroundColor
import com.chelo.smartstock.ui.theme.WhiteText


@Composable
fun ImageContainter(image: String?, navController: NavController, modifier: Modifier = Modifier) {

    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null && image != "")
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = "",
                    modifier = modifier.fillMaxSize()
                )
            else
                Image(
                    painterResource(R.drawable.defaul),
                    contentDescription = "",
                    modifier = modifier.fillMaxSize()
                )


        }
        FloatingActionButton(
            onClick = { Log.i("Chelo", "fob apretado") },
            shape = CircleShape,
            modifier = modifier.align(alignment = Alignment.BottomEnd),
            contentColor = WhiteText,
            backgroundColor = BackgroundColor
        ) { Icon(Icons.Default.Add, contentDescription = "") }

    }
}