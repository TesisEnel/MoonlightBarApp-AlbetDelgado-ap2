package com.kotlin.moonlightbarapp.ui.theme.MoonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.kotlin.moonlightbarapp.ui.theme.md_theme_light_surfaceTint
import com.kotlin.moonlightbarapp.ui.theme.morado_tema_claro_primary

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

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .size(width = 180.dp, height = 100.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coctel1),
                    contentDescription = "probando",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = cocktail.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nivel: Medio",
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun CocktailGrid(cocktails: List<Coctel>) {

    LazyVerticalGrid(

        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),



    ) {

        items(cocktails) { cocktail ->
            CocktailCard(cocktail)
        }
    }




}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CotelTopBar() {
    Scaffold(

        topBar = {
            TopAppBar(

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
                            color = morado_tema_claro_primary,
                            shadow = Shadow(Color.Yellow),
                            textAlign = TextAlign.Center

                        )



                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                            tint = morado_tema_claro_primary,

                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector =Icons.Filled.Bedtime,
                            contentDescription = "Localized description",
                            tint = md_theme_light_surfaceTint

                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {



                CocktailGrid(cocktails) // Aquí cambiamos LazyColumn por CocktailGrid
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent() {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Hinted search text") },
        modifier = Modifier

            .fillMaxWidth(),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
    )

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        CotelTopBar()

        }
    }
