package com.kotlin.moonlightbarapp.ui.moonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.ui.components.AddDecentImage
import com.kotlin.moonlightbarapp.ui.theme.DeepViolett40
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchedCocktail() {
    //val popular by viewModel.loadPopularScreen().collectAsStateWithLifecycle()

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
                CocktailLabel1()

            }
        }
    )
}


@Composable
fun IngredientsList1(cocktail: DrinkDto) {
    val ingredients = listOf(
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
    ).filterNotNull()

    for (ingredient in ingredients) {
        Text(text = ingredient, style = MaterialTheme.typography.titleMedium)
    }
}
//@Preview(showBackground = true)
@Composable
fun CocktailCard1() {
    // cóctel de ejemplo
    val cocktail = DrinkDto(
        strDrink = "Margarita",
        strDrinkThumb = "",
        strGlass = "Cocktail glass",
        strInstructions = "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
        strIngredient1 = "Tequila",
        strIngredient2 = "Triple sec",
        strIngredient3 = "Lime juice",
        strIngredient4 = "Salt",
        strMeasure1 = "1 1/2 oz ",
        strMeasure2 = "1/2 oz ",
        strMeasure3 = "1 oz "
    )

    Card(
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
                    modifier = Modifier.size(100.dp)
                )

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
fun CocktailLabel1(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(2.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        items(5) { cocktail ->
            CocktailCard1()
        }

    }
}



//@Composable
//fun CocktailCardsGrid() {
//    LazyColumn(modifier = Modifier.padding(top = 90.dp)) {
//        items(1) { _ ->  // Repite la estructura dos veces
//            LazyRow {
//                items(5) { _ ->  // Coloca dos CocktailCard uno al lado del otro
//                    CocktailCard()
//                }
//            }
//        }
//    }
//}