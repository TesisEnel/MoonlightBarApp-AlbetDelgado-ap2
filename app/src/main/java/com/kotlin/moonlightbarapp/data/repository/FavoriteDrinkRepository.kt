package com.kotlin.moonlightbarapp.data.repository

import com.kotlin.moonlightbarapp.data.local.dao.DrinkDao
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrink
import javax.inject.Inject

class FavoriteDrinkRepository @Inject constructor(
    private val drinkDao: DrinkDao
) {
    suspend fun save(drink: FavoriteDrink) = drinkDao.save(drink)
    suspend fun delete(drink: FavoriteDrink) = drinkDao.delete(drink)
    fun getAll() = drinkDao.getAll()
}