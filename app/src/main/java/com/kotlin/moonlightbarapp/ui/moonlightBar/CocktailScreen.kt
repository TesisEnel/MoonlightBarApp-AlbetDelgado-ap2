package com.kotlin.moonlightbarapp.ui.moonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.components.MyTextField
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.theme.Morado83


data class Coctel(val name: String)

val cocktails = listOf(
    Coctel("Romo"),
    Coctel("Cerveza"),
    Coctel("Cleren"),
    Coctel("Ani"),
    Coctel("Agua"),
    Coctel("Whiskey"),
    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailCard(cocktail: Coctel) {
    Card(
        onClick = { /* Do something */ },
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
                    Image(
                        painter = painterResource(id = R.drawable.coctel1),
                        contentDescription = "Prueba de cocktail",
                        modifier = Modifier.size(60.dp)
                    )

                    Text(
                        text = cocktail.name,
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
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description",
                 )
        } else {
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Localized description",
                tint = Morado83)
        }
    }
}



@Composable
fun CocktailGrid(cocktails: List<Coctel>) {

    LazyVerticalGrid(

        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(2.dp),
        modifier = Modifier.padding(20.dp)

    ) {
        items(cocktails) { cocktail ->
            CocktailCard(cocktail)
        }
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailTopBar() {
    var expanded by remember { mutableStateOf(false) }

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
                navigationIcon = {
                    IconButton(onClick = { expanded = true }) {
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
        bottomBar =
        {
            PieDePagina()
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
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 300.dp, top = 16.dp)
                    ) {
                        Icon(Icons.Filled.Shuffle, "Localized description")
                    }


                }
                MenuSample(expanded = expanded, onDismiss = { expanded = false })

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
fun MenuSample(expanded: Boolean, onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() }
        ) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { /* Handle edit! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = { /* Handle settings! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null
                    )
                })

            DropdownMenuItem(
                text = { Text("Send Feedback") },
                onClick = { /* Handle send feedback! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) })
        }
    }
}


@Composable
fun PieDePagina() {
    var selectedItem by remember { mutableStateOf(0) }
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
                    when (val icon = icons[index]) {
                        is ImageVector -> Icon(icon, contentDescription = item)
                    }
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // CustomArc()
        CocktailTopBar()
    }
}


