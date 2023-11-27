package com.kotlin.moonlightbarapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kotlin.moonlightbarapp.ui.theme.Morado83

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
                width = 2.dp,
                color = Morado83,
                shape = CircleShape
            )
            .clip(CircleShape)
            .aspectRatio(1f, true)
    )
}