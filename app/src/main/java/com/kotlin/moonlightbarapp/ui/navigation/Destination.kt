package com.kotlin.moonlightbarapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val route: String, val icon: ImageVector, val title: String) {
    object MoonBar : Destination(
        route = "MoonBar", icon = Icons.Filled.Bedtime,
        title = "Moon Bar"
    )

    object Favoritos : Destination(
        route = "Favoritos", icon = Icons.Filled.Favorite,
        title = "Favoritos"
    )object Categoria : Destination(
        route = "Categoria", icon = Icons.Filled.Category,
        title = "Categoria"
    )

    companion object {
        val toList = listOf(MoonBar, Categoria,Favoritos)
    }
}