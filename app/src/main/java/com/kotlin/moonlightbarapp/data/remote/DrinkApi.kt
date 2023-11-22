package com.kotlin.moonlightbarapp.data.remote

import com.kotlin.moonlightbarapp.data.remote.dto.drinks
import retrofit2.http.GET

interface DrinkApi {
    @GET("/api/json/v2/9973533/randomselection.php")
    suspend fun getRandomCocktails(): drinks

}