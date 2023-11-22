package com.kotlin.moonlightbarapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun AddImage(
    url: String,
    modifier: Modifier = Modifier,
    description: String,
) {
    val painter = rememberAsyncImagePainter(model = url)
    Image(
        painter = painter,
        contentDescription = description,
        modifier = modifier
            .border(
                width = 5.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            )
            .clip(CircleShape)
            .aspectRatio(1f, true)
    )
}