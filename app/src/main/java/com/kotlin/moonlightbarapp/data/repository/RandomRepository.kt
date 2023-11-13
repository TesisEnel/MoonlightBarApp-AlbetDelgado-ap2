package com.kotlin.moonlightbarapp.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.kotlin.moonlightbarapp.data.RandomApi
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RandomRepository (private var api: RandomApi) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getRandomCocktail(): Flow<Resource<DrinkDto>> = flow {
        try {
            emit(Resource.Loading())

            val gastos = api.getCocktailRandom()

            emit(Resource.Success(gastos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error("Verificar tu conexión a internet"))
        }
    }
}