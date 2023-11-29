package com.kotlin.moonlightbarapp.data.repository

import com.kotlin.moonlightbarapp.data.remote.DrinkApi
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DrinkRepository @Inject constructor(
    private val api: DrinkApi
) {
    fun getRandomCocktail(): Flow<Resource<List<DrinkDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val drinks = api.getRandomCocktails()

            emit(Resource.Success(drinks.drinks))
        } catch (e: HttpException) {
            if (e.code() == 429) {
                delay(5000)
                emit(Resource.Error("Demasiadas solicitudes. Esperando antes de volver a intentar."))
            } else {
                emit(Resource.Error(e.message ?: "Error HTTP"))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexión a internet"))
        }
    }

    fun getPopularCocktail(): Flow<Resource<List<DrinkDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val drinks = api.getPopularCocktails()

            emit(Resource.Success(drinks.drinks))
        } catch (e: HttpException) {
            if (e.code() == 429) {
                delay(5000)
                emit(Resource.Error("Demasiadas solicitudes. Esperando antes de volver a intentar."))
            } else {
                emit(Resource.Error(e.message ?: "Error HTTP"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Verificar tu conexión a internet"))
        }
    }

    suspend fun searchCocktailByName(cocktailName: String): DrinkDto {
        val drinks = api.searchCocktail(cocktailName).drinks
        return drinks?.firstOrNull() ?: throw Exception("Cocktail not found")
    }

    fun searchCocktailByLetter(letter: String): Flow<Resource<List<DrinkDto>?>> = flow {
        try {
            emit(Resource.Loading())

            val drinks = api.searchCocktailByLetter(letter)

            emit(Resource.Success(drinks.drinks))
        } catch (e: HttpException) {
            if (e.code() == 429) {
                delay(5000)
                emit(Resource.Error("Demasiadas solicitudes. Esperando antes de volver a intentar."))
            } else {
                emit(Resource.Error(e.message ?: "Error HTTP"))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexión a internet"))
        }
    }
}

