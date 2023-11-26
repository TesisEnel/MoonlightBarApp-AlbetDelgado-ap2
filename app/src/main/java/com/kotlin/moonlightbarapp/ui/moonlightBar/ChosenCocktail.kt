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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kotlin.moonlightbarapp.ui.components.AddImage
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChosenCocktail(id: String, viewModel: DrinkViewModel, navController: NavController ) {

    DisposableEffect(Unit) {
        viewModel.getCocktailById(id)
        onDispose {
        }
    }

    println("El id del screen: ${viewModel.drink.idDrink}")

    val ingredientsWithImages = listOfNotNull(
        viewModel.drink.strIngredient1?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient2?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient3?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient4?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient5?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient6?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient7?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient8?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient9?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient10?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient11?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient12?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient13?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient14?.let { it to getIngredientImageUrl(it) },
        viewModel.drink.strIngredient15?.let { it to getIngredientImageUrl(it) },
    )

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
                    IconButton(onClick = {navController.navigate(Destination.MoonBar.route)}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
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
        AddImage(
            url = viewModel.drink.strDrinkThumb,
            description = "Image"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(95.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {

            IngredientsList(ingredients = ingredientsWithImages)
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
                    text = viewModel.drink.strInstructions
                )
            }
        }
    }
}

@Composable
fun IngredientsList(ingredients: List<Pair<String, String>>) {

    val scrollState = rememberLazyListState()
    val progress = scrollState.firstVisibleItemIndex.toFloat() / (ingredients.size - 1)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = Morado40)
                .padding(2.dp)
        ) {
            items(ingredients) { (name, url) ->
                IngredientCard(name = name, imageUrl = url)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun IngredientCard(name: String, imageUrl: String) {
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
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
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
            overflow = TextOverflow.Visible,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun getIngredientImageUrl(ingredientName: String?): String{
    return "https://www.thecocktaildb.com/images/ingredients/${ingredientName}-Small.png"
}

