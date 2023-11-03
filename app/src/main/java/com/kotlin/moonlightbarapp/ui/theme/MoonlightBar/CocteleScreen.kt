package com.kotlin.moonlightbarapp.ui.theme.MoonlightBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R

data class Coctel (val name: String)
val cocktails = listOf(
    Coctel("Romo"),
    Coctel("Cerveza"),
    Coctel("Cleren"),
    Coctel("Ani"),
    Coctel("Agua"),
    Coctel("Wiki")
)


@Composable
fun CocktailCard(cocktail: Coctel) {
    Column(modifier = Modifier.padding(10.dp)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            //elevation = CardDefaults.(defaultElevation = 4.dp),
            modifier = Modifier.size(width = 180.dp, height = 100.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.coctel1),
                    contentDescription = "probando",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = cocktail.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )


            }
            Row {
                Text(
                    text = "Nivel: Medio",
                    modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                )
            }
        }


    }
}

@Composable
fun CocktailList(cocktails: List<Coctel>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(cocktails) { cocktail ->
            CocktailCard(cocktail)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailsSearch(){
    Column {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Search Cocktails") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {

      CocktailList(cocktails)
        }
    }
