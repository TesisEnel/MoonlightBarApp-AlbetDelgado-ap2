package com.kotlin.moonlightbarapp.ui.moonlightBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.ui.components.AddDecentImage
import com.kotlin.moonlightbarapp.ui.theme.DeepViolett40
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostPopularCocktails(viewModel: DrinkViewModel,navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                    IconButton(onClick = { /* doSomething() */ }) {
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
                    text = "The Most Popular cocktails",
                    style = MaterialTheme.typography.headlineMedium,
                    fontStyle = FontStyle.Italic,
                    color = DeepViolett40,
                    modifier = Modifier.padding(top = 40.dp, start = 5.dp),
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
                    CocktailLabel(uiState.popularDrinks,navController)
                }
            }
        }
    )
}

@Composable
fun IngredientsList(cocktail: DrinkDto) {
    val ingredients = listOfNotNull(
        cocktail.strIngredient1,
        cocktail.strIngredient2,
        cocktail.strIngredient3,
        cocktail.strIngredient4,
        cocktail.strIngredient5,
        cocktail.strIngredient6,
        cocktail.strIngredient7,
        cocktail.strIngredient8,
        cocktail.strIngredient9,
        cocktail.strIngredient10
    )

    for (ingredient in ingredients) {
        Text(text = ingredient, style = MaterialTheme.typography.titleMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailCard(cocktail: DrinkDto,navController: NavController) {
    Card(
        onClick = { navController.navigate("${Destination.ChosenCocktail.route}/${cocktail.strDrink}") },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(15.dp)
            .size(width = 250.dp, height = 170.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
               AddDecentImage(
                   url = cocktail.strDrinkThumb,
                   description = "Image",
                   modifier = Modifier.size(85.dp)
               )
                Column(
                    modifier = Modifier.padding(start = 70.dp)
                ) {
                    Text(
                        text = "Ingredients:",
                        style = MaterialTheme.typography.titleSmall
                    )
                    IngredientsList(cocktail)
                }
            }
            Divider()

            Text(
                text = cocktail.strDrink,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, start = 15.dp)
            )
        }
    }
}
@Composable
fun CocktailLabel(cocktail: List<DrinkDto>, navController: NavController){
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(2.dp),
        modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
    items(cocktail) { cocktail ->
        CocktailCard(cocktail,navController)
        }
    }
}
