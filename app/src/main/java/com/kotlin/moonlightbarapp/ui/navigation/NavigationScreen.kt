package com.kotlin.moonlightbarapp.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.moonlightBar.ChosenCocktail
import com.kotlin.moonlightbarapp.ui.moonlightBar.CocktailTopBar
import com.kotlin.moonlightbarapp.ui.moonlightBar.FavoriteCocktail
import com.kotlin.moonlightbarapp.ui.moonlightBar.PieDePagina
import com.kotlin.moonlightbarapp.ui.moonlightBar.mostPopularCocktails
import com.kotlin.moonlightbarapp.ui.theme.MoonlightBarAppTheme
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            PieDePagina(navController = navController)
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavigation(navController = navController)
            }
        }
    )
}

@Composable
fun AppNavigation(navController: NavHostController) {

    val viewModel: DrinkViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Destination.MoonBar.route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(navController, startDestination = Destination.MoonBar.route) {
        composable(Destination.MoonBar.route) {
            CocktailTopBar(viewModel, navController)
        }
        composable(Destination.Populares.route) {
            mostPopularCocktails()
        }
        composable(Destination.Favoritos.route) {
            FavoriteCocktail(viewModel)
        }
        composable("${Destination.ChosenCocktail.route}/{id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType})
        ) { capture ->
            val id = capture.arguments?.getInt("id") ?: 0
            ChosenCocktail(id = id.toString(),viewModel, navController)
        }
    }
}

@Composable
fun SplashScreen() {
    MoonlightBarAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Morado40),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoblanco_p),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
            Text(text = "Moonlight Bar App",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Cursive,
                fontSize = 30.sp
            )
        }
    }
}



