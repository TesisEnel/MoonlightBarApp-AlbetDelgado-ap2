package com.kotlin.moonlightbarapp.ui.moonlightBar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChosenCocktail() {
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
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
    ) {}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
            .background(color = Morado40),
        contentAlignment = Alignment.TopCenter

    ) {
        Image(
            painter = painterResource(id = R.drawable.coctel1),
            contentDescription = "Login Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)

        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(95.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            IngredientsList(ingredients = ingredients)
        }
        Spacer(modifier = Modifier.height(10.dp))


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            contentAlignment = Alignment.BottomCenter,
        )
        {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                color = Color.White,
                shape = RoundedCornerShape(
                    topStartPercent = 8,
                    topEndPercent = 10
                )
            )

            {

                Text(modifier = Modifier
                    .height(5.dp)
                    .padding(top = 20.dp)
                    .padding(start = 10.dp),
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    text = "Instrucciones:")
                Text(
                    modifier = Modifier
                        .height(5.dp)
                        .padding(top = 40.dp)
                        .padding(start = 10.dp)
                        .padding(end = 15.dp),
                    overflow = TextOverflow.Visible,

                    text = "¡Gelatina embriagadora para tod@s! ¿List@s para empezar? Para iniciar nuestro original cóctel, sigue estos pasos:\n" +
                            "\n" +
                            "Disuelve la gelatina en 225 ml de agua hirviendo, remueve hasta que se disuelva por completo.\n" +
                            "Luego, espera hasta que la mezcla alcance temperatura ambiente (casi fría), agrega el vodka y el agua restante (los otros 225 ml), remueve muy bien.\n" +
                            "En este punto tú eliges cómo servirlo, aquí tiene dos opciones: rellena las fresas enteras con la gelatina embriagante o rellena los vasos pequeños de cóctel con la mezcla de gelatina de fresa y vodka.\n" +
                            "Lleva los cócteles a la nevera durante 3 horas y…, ¡hora de brindar!" +
                            "¡Manos a la obra! Alista todo lo necesario para comenzar este delicioso, original y refrescante cóctel.\n" +
                            "\n" +
                            "Primero prepara la sandía, pues te servirá de recipiente. Corta uno de los extremos pero sin excederte, así servirá de base, luego corta el otro extremo, lo suficiente para verter luego los ingredientes dentro de el.\n" +
                            "Retira una pequeña parte de la pulpa y reserva, el resto déjalo dentro de la sandía y tritúralo con una batidora manual (hazlo con cuidado para no dañar la cascara de la fruta).\n" +
                            "Cuando el centro de la sandía sea zumo, es el momento de agregar el resto de los ingredientes, uno a uno. Recuerda, puedes cambiar las cantidades de cada ingrediente al gusto y… ¡a disfrutar!\n" +
                            "¿Prefieres los detalles y el paso a paso? ¡No te pierdas el vídeo!\n" +
                            "\n" +
                            "Consejo: Recuerda manipular con mucho cuidado la sandía, porque se convertirá en tú vaso de cóctel"
                )

            }
        }
    }
}

@Composable
fun IngredientsList(ingredients: List<Pair<String, Int>>) {
    val scrollState = rememberLazyListState()
    val progress = scrollState.firstVisibleItemIndex.toFloat() / (ingredients.size - 1)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Scroll horizontal de ingredientes
        LazyRow(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = Morado40)
                .padding(2.dp)
        ) {
            items(ingredients) { (name, imageRes) ->
                IngredientCard(name = name, imageRes = imageRes)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        // Barra de progreso
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun IngredientCard(name: String, imageRes: Int) {
    Column(
        modifier = Modifier
            .width(85.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
                .background(Color.White),
            //contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            )
        }
        Text(
            text = name,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            maxLines = 4,
            overflow = TextOverflow.Visible,  // Permite que el texto se extienda más allá de su caja contenedora
            modifier = Modifier.fillMaxWidth()

        )
    }
}


var ingredients = listOf(
    Pair("Ingrediente 1", R.drawable.licor),
    Pair("Ingrediente 2", R.drawable.licor__1_),
    Pair("Ingrediente 3", R.drawable.licor__2_),
    Pair("Ingrediente 4", R.drawable.licor__3_),
    Pair("Ingrediente 5", R.drawable.licor)
)
