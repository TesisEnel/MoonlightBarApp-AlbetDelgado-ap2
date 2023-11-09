package com.kotlin.moonlightbarapp.ui.moonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.moonlightbarapp.R
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kotlin.moonlightbarapp.ui.componentes.MyTextField
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40

data class Coctel(val name: String)

val cocktails = listOf(
    Coctel("Romo"),
    Coctel("Cerveza"),
    Coctel("Cleren"),
    Coctel("Ani"),
    Coctel("Agua"),
)


@Composable
fun CocktailCard(cocktail: Coctel) {

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
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
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()


                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",


                            )
                    }
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
            Box(modifier = Modifier.padding(innerPadding)) {
                CustomArc()
                Column {
                    MyTextField(
                        modificador = Modifier.offset(y = (-5).dp),
                        valor = "",
                        alCambiarValor = {},
                        iconoDerecho = {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                        },
                        iconoIzquierdo = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        },
                        textoQueDesaparece = "Buscar Coctel"

                    )

                    CocktailGrid(cocktails)

                    FloatingActionButton(
                        onClick = { /* do something */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }

                }

            }



        }

    )

}
@Composable
fun FloatingActionButtonSample() {
    FloatingActionButton(
        onClick = { /* do something */ },
    ) {
        Icon(Icons.Filled.Add, "Localized description")
    }
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
    Canvas(modifier = Modifier.size(width = 1000.dp, height = 60.dp).offset(y = 61.dp))
    {
        drawArc(
            color = Morado100,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset.Zero,
            size = this.size,
            //style = Stroke(150f)

        )
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // CustomArc()
        CotelTopBar()

    }
}
