package com.kotlin.moonlightbarapp.data.remote

import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.data.remote.dto.drinks
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {
    @GET("/api/json/v2/9973533/randomselection.php")
    suspend fun getRandomCocktails(): drinks

    @GET("/api/json/v2/9973533/lookup.php")
    suspend fun getCocktailById(@Query("i") idDrink: String) : DrinkDto?


}