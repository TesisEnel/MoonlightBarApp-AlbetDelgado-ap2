package com.kotlin.moonlightbarapp.data.repository

import com.kotlin.moonlightbarapp.data.remote.DrinkApi
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val api: DrinkApi
) {
    fun getRandomCocktail(): Flow<Resource<List<DrinkDto>>> = flow {
        try {
            emit(Resource.Loading())

            val drinks = api.getRandomCocktails()

            emit(Resource.Success(drinks.drinks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }


}