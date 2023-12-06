
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.ui.components.AddDecentImage
import com.kotlin.moonlightbarapp.ui.components.MyTextField
import com.kotlin.moonlightbarapp.ui.theme.DeepViolett40
import com.kotlin.moonlightbarapp.ui.theme.Morado100
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.theme.Morado83
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchedCocktail(viewModel: DrinkViewModel = hiltViewModel(),navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var textFieldValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var showSnackbar by remember { mutableStateOf(false) }

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
                actions = {
                    IconButton(onClick = {navController.navigate(Destination.MoonBar.route)}) {
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
                    text = "Search a Cocktail",
                    style = MaterialTheme.typography.headlineMedium,
                    fontStyle = FontStyle.Italic,
                    color = DeepViolett40,
                    modifier = Modifier.padding(top = 40.dp, start = 5.dp),
                )

                MyTextField(
                    modificador = Modifier
                        .offset(y = (-5).dp),
                    valor = textFieldValue,
                    alCambiarValor = { newValue -> textFieldValue = newValue },
                    iconoDerecho = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CheckboxWithMoreOptions(viewModel)
                            }
                        }
                    },
                    iconoIzquierdo = {
                        IconButton(onClick = {
                            keyboardController?.hide()
                            val cocktailFound = uiState.drinksByLetter.find {
                                it.strDrink.lowercase() == textFieldValue.lowercase()
                            }
                            if (cocktailFound != null) {
                                navController.navigate("${Destination.ChosenCocktail.route}/${cocktailFound.strDrink}")
                            } else {
                                showSnackbar = true
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    textoQueDesaparece = "Type a cocktail",
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            val cocktailFound = uiState.drinksByLetter.find {
                                it.strDrink.lowercase() == textFieldValue.lowercase()
                            }
                            if (cocktailFound != null) {
                                navController.navigate("${Destination.ChosenCocktail.route}/${cocktailFound.strDrink}")
                            } else {
                                showSnackbar = true
                            }
                        })
                )

// Filtrar los cócteles por lo escrito
                val filteredCocktails = uiState.drinksByLetter.filter {
                    it.strDrink.lowercase().contains(textFieldValue.lowercase())
                }

//  cócteles filtrados
                CocktailLabel1(filteredCocktails, navController, viewModel)
            }
        })
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailCard1(
    cocktail: DrinkDto,
    navController: NavController,
    viewModel: DrinkViewModel = hiltViewModel()
) {
    val favorites by viewModel.favoriteDrinks.collectAsStateWithLifecycle()
    var favoriteOn by mutableStateOf(false)
    val currentFavorite = favorites.find {
        it.strDrink == cocktail.strDrink
    }

    if(currentFavorite != null){
        favoriteOn = true
    }

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
                    modifier = Modifier.size(100.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp,  bottom = 5.dp)
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())

            Text(
                text = cocktail.strDrink,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp, start = 15.dp)
            )
            IconToggleButton(
                checked = favoriteOn,
                onCheckedChange = { favoriteOn = it },
                modifier = Modifier
                    .padding(top = 2.dp)
                    .align(Alignment.End)
            ) {
                if (favoriteOn) {
                    Icon(
                        Icons.Filled.Favorite, contentDescription = "Localized description",
                    )
                    if (favorites.find { it.strDrink == cocktail.strDrink } == null) {
                        viewModel.save(cocktail)
                    }
                } else {
                    Icon(
                        Icons.Outlined.FavoriteBorder, contentDescription = "Localized description",
                        tint = Morado83
                    )
                    if (currentFavorite != null) {
                        viewModel.delete(currentFavorite)
                    }
                }
            }

        }
    }
}

@Composable
fun CocktailLabel1(
    cocktails: List<DrinkDto>,
    navController: NavController,
    viewModel: DrinkViewModel = hiltViewModel()
)
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(2.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(cocktails) { cocktail ->
            CocktailCard1(cocktail,navController,viewModel)
        }
    }
}
@Composable
fun CheckboxWithMoreOptions(viewModel: DrinkViewModel) {
    var Checks by remember { mutableStateOf(false) }

    val ingredients = listOfNotNull(
        viewModel.drink.strIngredient1,
        viewModel.drink.strIngredient2,
        viewModel.drink.strIngredient3,
        viewModel.drink.strIngredient4,
        viewModel.drink.strIngredient5,
        viewModel.drink.strIngredient6,
        viewModel.drink.strIngredient7,
        viewModel.drink.strIngredient8,
        viewModel.drink.strIngredient9,
        viewModel.drink.strIngredient10,
        viewModel.drink.strIngredient11,
        viewModel.drink.strIngredient12,
        viewModel.drink.strIngredient13,
        viewModel.drink.strIngredient14,
        viewModel.drink.strIngredient15
    )
    val ingredientStates = remember { mutableStateMapOf<String, Boolean>() }
    ingredients.forEach { ingredient ->
        ingredientStates[ingredient] = false
    }

    Column {
        Checkbox(
            checked = Checks,
            onCheckedChange = { isChecked ->
                Checks = isChecked
                // Cuando el checkbox principal se selecciona, seleccionar todos los ingredientes
                if (isChecked) {
                    ingredientStates.keys.forEach { ingredient ->
                        ingredientStates[ingredient] = true
                    }
                }

                else {
                    ingredientStates.keys.forEach { ingredient ->
                        ingredientStates[ingredient] = false
                    }
                }
            }
        )
        Text(text = "Ingredientes")

        if (Checks) {
            ingredientStates.forEach { (ingredient, isChecked) ->
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isIngredientChecked ->
                        ingredientStates[ingredient] = isIngredientChecked
                    }
                )
                Text(text = ingredient)
            }
        }
    }
}