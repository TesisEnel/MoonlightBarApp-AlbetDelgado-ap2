package com.kotlin.moonlightbarapp.ui.pruebas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto

@Composable
fun IngredientsList(cocktail: DrinkDto) {
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
@Preview(showBackground = true)
@Composable
fun PreviewCocktailCard() {
    // Crear un cóctel de ejemplo
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
            .size(width = 250.dp, height = 150.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.coctel1), // Reemplaza esto con tu imagen
                    contentDescription = "Image",
                    modifier = Modifier.size(100.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
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
                    .padding(top = 8.dp)
            )
        }
    }
}
