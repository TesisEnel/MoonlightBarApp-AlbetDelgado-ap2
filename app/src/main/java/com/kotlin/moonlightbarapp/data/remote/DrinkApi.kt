package com.kotlin.moonlightbarapp.data.remote

import com.kotlin.moonlightbarapp.data.remote.dto.drinks
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {
    @GET("/api/json/v2/9973533/randomselection.php")
    suspend fun getRandomCocktails(): drinks

    @GET("/api/json/v2/9973533/popular.php")
    suspend fun getPopularCocktails(): drinks

    @GET("/api/json/v2/9973533/search.php")
    suspend fun searchCocktail(@Query("s") cocktailName: String): drinks
    @GET("/api/json/v2/9973533/search.php")
    suspend fun searchCocktailByLetter(@Query("f") letter: String): drinks
}