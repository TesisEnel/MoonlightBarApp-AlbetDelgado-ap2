package com.kotlin.moonlightbarapp.ui.moonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.ui.components.AddImage
import com.kotlin.moonlightbarapp.ui.components.MyTextField
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.theme.Morado83
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailCard(cocktail: DrinkDto, navController: NavController) {

    Card(
        onClick = { navController.navigate(Destination.ChosenCocktail.route) },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(15.dp)
            .size(width = 180.dp, height = 100.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AddImage(
                        url = cocktail.strDrinkThumb,
                        description = "Imagen"
                    )
                    Text(
                        text = cocktail.strDrink,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
            FavoriteButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun FavoriteButton(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = modifier
    ) {
        if (checked) {
            Icon(
                Icons.Filled.Favorite, contentDescription = "Localized description",
            )
        } else {
            Icon(
                Icons.Outlined.FavoriteBorder, contentDescription = "Localized description",
                tint = Morado83
            )
        }
    }
}


@Composable
fun CocktailGrid(cocktails: List<DrinkDto>, navController: NavController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(2.dp),
        modifier = Modifier.padding(20.dp)

    ) {
        items(cocktails) { cocktail ->
            CocktailCard(cocktail,navController )
        }
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CocktailTopBar(viewModel: DrinkViewModel, navController: NavController) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var textFieldValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = { /* ... */ },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                CustomArc()
                Column {
                    MyTextField(
                        modificador = Modifier.offset(y = (-5).dp),
                        valor = textFieldValue,
                        alCambiarValor = { newValue -> textFieldValue = newValue },
                        iconoDerecho = {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                        },
                        iconoIzquierdo = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        },
                        textoQueDesaparece = "Buscar Coctel",
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                    )

                    if (uiState.isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        CocktailGrid(uiState.drinks.take(6), navController)
                    }

                    FloatingActionButton(
                        onClick = { viewModel.loadScreen() },
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 301.dp, top = 16.dp)
                    ) {
                        Icon(Icons.Filled.Shuffle, "Localized description")
                    }
                }
            }
        }
    )
}

@Composable
fun CustomArc() {
    Canvas(modifier = Modifier.size(width = 1000.dp, height = 90.dp))
    {
        drawRect(
            color = Morado40,
            topLeft = Offset.Zero,
            )
    }
    Canvas(
        modifier = Modifier
            .size(width = 1000.dp, height = 60.dp)
            .offset(y = 61.dp)
    )
    {
        drawArc(
            color = Morado100,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset.Zero,
            size = this.size,
        )
    }
}

@Composable
fun PieDePagina(navController: NavController) {

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Moon Bar", "Categoria", "Favoritos")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Category,
        Icons.Filled.Favorite
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (index) {
                        0 -> navController.navigate(Destination.MoonBar.route)
                        1 -> navController.navigate(Destination.Categoria.route)
                        2 -> navController.navigate(Destination.Favoritos.route)
                    }
                }
            )
        }
    }
}



