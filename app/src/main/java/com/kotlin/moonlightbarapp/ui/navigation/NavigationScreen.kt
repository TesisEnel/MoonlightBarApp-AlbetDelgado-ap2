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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kotlin.moonlightbarapp.R
import com.kotlin.moonlightbarapp.ui.moonlightBar.CardFooter
import com.kotlin.moonlightbarapp.ui.moonlightBar.ChosenCocktail
import com.kotlin.moonlightbarapp.ui.moonlightBar.CocktailTopBar
import com.kotlin.moonlightbarapp.ui.moonlightBar.FavoriteCocktail
import com.kotlin.moonlightbarapp.ui.moonlightBar.MostPopularCocktails
import com.kotlin.moonlightbarapp.ui.moonlightBar.SearchedCocktail
import com.kotlin.moonlightbarapp.ui.theme.MoonlightBarAppTheme
import com.kotlin.moonlightbarapp.ui.theme.Morado40
import com.kotlin.moonlightbarapp.ui.viewmodel.DrinkViewModel
import com.kotlin.moonlightbarapp.util.Destination
import kotlinx.coroutines.delay


private const val SplashWaitTime: Long = 2000

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Destination.Splash.route) {
                CardFooter(navController = navController)
            }
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

    NavHost(navController, startDestination = Destination.Splash.route) {
        composable(Destination.Splash.route) {
            LandingScreen(onTimeout = { navController.navigate(Destination.MoonBar.route) })
        }
        composable(Destination.MoonBar.route) {
            CocktailTopBar(viewModel, navController)
        }
        composable(Destination.Populares.route) {
            MostPopularCocktails(viewModel,navController)
        }
        composable(Destination.Favoritos.route) {
            FavoriteCocktail(viewModel, navController)
        }
        composable("${Destination.ChosenCocktail.route}/{cocktailName}",
            arguments = listOf(navArgument("cocktailName") { type = NavType.StringType })
        ) { capture ->
            val cocktailName = capture.arguments?.getString("cocktailName") ?: ""
            ChosenCocktail(cocktailName = cocktailName, viewModel, navController)
        }
        composable(Destination.SearchCocktail.route) {
            SearchedCocktail()
        }
    }
}

@Composable
fun LandingScreen(onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(Unit) {
        delay(SplashWaitTime)
        currentOnTimeout()
    }

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




