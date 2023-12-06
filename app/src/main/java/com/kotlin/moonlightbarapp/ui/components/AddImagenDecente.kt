package com.kotlin.moonlightbarapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kotlin.moonlightbarapp.ui.theme.Morado83


@Composable
fun AddImagenCuadradada(
    url: String,
    modifier: Modifier = Modifier,
    description: String,
) {
    val painter = rememberAsyncImagePainter(model = url)
    Image(
        painter = painter,
        contentDescription = description,
        modifier = modifier
            .clip(RectangleShape)
            .aspectRatio(1f, true)
            .border(width = 3.dp,color= Morado83)
            .shadow(elevation = 7.dp)
    )
}