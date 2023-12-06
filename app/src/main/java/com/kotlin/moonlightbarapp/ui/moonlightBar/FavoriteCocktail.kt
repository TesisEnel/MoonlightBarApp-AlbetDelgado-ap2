package com.kotlin.moonlightbarapp.ui.moonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrink
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.ui.components.AddImage
import com.kotlin.moonlightbarapp.ui.theme.DeepViolett40
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.theme.Morado83
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteCocktail(viewModel: DrinkViewModel, navController: NavController) {
    val favorites by viewModel.favoriteDrinks.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Morado40,
                    titleContentColor = Morado100,
                    navigationIconContentColor = Morado100,
                    actionIconContentColor = Morado100
                ),
                title = {
                    Text(
                        "Moonlight Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow(Color.Yellow),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Destination.MoonBar.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Bedtime,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Favorites cocktails",
                    style = MaterialTheme.typography.headlineMedium,
                    fontStyle = FontStyle.Italic,
                    color = DeepViolett40,
                    modifier = Modifier.padding(top = 16.dp)
                )
                CocktailLabel(favorites, navController)
            }
        }
    )
}

@Composable
fun CocktailLabel(cocktails: List<FavoriteDrink>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(2.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(cocktails) { cocktail ->
            CocktailFavoriteCard(cocktail, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun CocktailFavoriteCard(
    cocktail: FavoriteDrink,
    navController: NavController,
    viewModel: DrinkViewModel = hiltViewModel()
) {

    val favorites by viewModel.favoriteDrinks.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var favoriteOn by mutableStateOf(false)
    var showDialog by remember { mutableStateOf(false) }
    val currentFavorite = favorites.find {
        it.strDrink == cocktail.strDrink
    }

    if (currentFavorite != null) {
        favoriteOn = true
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = { Text(text = "Delete your favorites") },
            text = { Text("¿Are you sure you want to remove this cocktail from your favorites?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        favoriteOn = false
                        if (currentFavorite != null) {
                            viewModel.delete(currentFavorite)
                        }
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
    Card(
        onClick = { navController.navigate("${Destination.ChosenCocktail.route}/${cocktail.strDrink}") },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(15.dp)
            .size(width = 250.dp, height = 100.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddImage(
                    url = cocktail.strDrinkThumb,
                    description = "Image"
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = cocktail.strDrink,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = cocktail.strGlass,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            IconToggleButton(
                checked = favoriteOn,
                onCheckedChange = {
                    if (!it) {
                        showDialog = true
                    } else {
                        favoriteOn = it
                        if (favorites.find { it.strDrink == cocktail.strDrink } == null) {
                            val currentCocktail: DrinkDto? = uiState.drinks.find {
                                it.strDrink == cocktail.strDrink
                            }
                            if (currentCocktail != null) {
                                viewModel.save(currentCocktail)
                            }
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                if (favoriteOn) {
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
    }
}
