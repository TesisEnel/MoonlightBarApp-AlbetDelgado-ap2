package com.kotlin.moonlightbarapp.data

import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto

import retrofit2.http.GET


interface RandomApi {
    @GET("api/json/v1/1/random.php")
    suspend fun getCocktailRandom():DrinkDto



}